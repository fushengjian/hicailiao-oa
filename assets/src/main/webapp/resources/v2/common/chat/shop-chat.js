//XMPP服务器BOSH地址
var BOSH_SERVICE = '';

var tigase_domain = '';
//XMPP连接
var connection = null;

//当前状态是否连接
var connected = false;

//当前登录的JID
var jid = '';

var sid = '';

//当前联系人
var contacts_name = '';

//消息总记录数
var totalCount;

//查看更多索引
var checkMoreIndex;

//查看更多点击次数
var checkMoreCount = 0;

//剩余连接次数
var remainConnectCount = 5;

//连接等待时间
var wait = 5;

//连接状态改变的事件
function onConnect(status) {
    //console.log(status)
    if (status == Strophe.Status.CONNFAIL) {
        alert("连接失败！");
    } else if (status == Strophe.Status.AUTHFAIL) {
        alert("登录失败！");
    } else if (status == Strophe.Status.DISCONNECTED) {
        connected = false;
        if (remainConnectCount > 0) {
        	 reconnect();
        } else {
        	jQuery(".loading_words").text("无法连接到服务器，请检查网络或者尝试刷新当前页面！");
        	jQuery(".chat_loading").show();
        }
    } else if (status == Strophe.Status.CONNECTED) {
        connected = true;

        remainConnectCount = 5;
        //当接收到<message>节，调用onMessage回调函数
        connection.addHandler(onMessage, null, 'message', null, null, null);
        //首先要发送一个<presence>给服务器（initial presence）
        connection.send($pres().tree());
        //获取好友列表
        getContacts();
    }
}

//重新连接
function reconnect() {
	jQuery(".chat_loading").show();
	jQuery(".loading_words").text("连接断开，正在尝试重新连接...");
	connection.connect(jid + "@" + tigase_domain, sid, onConnect, wait);
	remainConnectCount--;
}

//接收到消息
function onMessage(msg) {
    var type = msg.getAttribute('type');
    var from = msg.getAttribute('from');
    var elems = msg.getElementsByTagName('body');
    if (type == "chat" && elems.length > 0) {
    	var from_name = from.split("@")[0];
    	if (from_name == contacts_name) { //新消息发送者为当前聊天对象
    		var body = elems[0];
    		//TODO 时间应该以发送时间为准
    		var date = new Date();
    		var month = date.getMonth() + 1;
    		var timeStr = date.getFullYear() + "-" + month + "-" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
    		var content = escape(Strophe.getText(body));
    		
    		var message = '<dl class="vhe m1"><dt><a href="#"><img src="' + contacts_logo + '" alt=""/></a>'
    			+'<ul><li><div style="display: inline-block; margin-right: 9px;">' + contacts_call_name + '<span>' + timeStr + '</span></div></li></ul></dt><dd>' + content +'</dd></dl>';
    		
    		jQuery("#chat_content").append(message);
    		var chat_content = jQuery("#chat_content")[0];
    		chat_content.scrollTop = chat_content.scrollHeight;
    	} else if(jQuery(".chat_left_content>dl#" + from_name).length > 0) { //新消息发送者存在于其他选项卡中
    		jQuery(".chat_left_content>dl#" + from_name).css("display", "").attr("class", "onmsg"); //新消息提醒
    	} else { //新消息发送者不存在于选项卡中,新建一个选项卡
    		createContactsCard(from_name);
    	}
    	//将新接收到的消息联系人排到联系人面板的最前边
		jQuery(".chat_left_content>dl#" + from_name).prependTo(".chat_left_content");
    }
    return true;
}
//发送消息
function sendMessage(content) {
	 if(connected) {
         if(contacts_name == "") {
        		 alert("没有选中任何联系人！");
        		 return;
         }
         //创建一个<message>元素并发送
         var msg = $msg({
             to:contacts_name + "@" + tigase_domain, 
             from:jid + "@" + tigase_domain,
             type: 'chat',
         }).c("body", null, content);
         connection.send(msg.tree());

         var date = new Date();
         var month = date.getMonth() + 1;
 		 var timeStr = date.getFullYear() + "-" + month + "-" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
         var message = '<dl class="me m1"><dt><a href="#"><img src="' + user_logo + '" alt=""/></a>'
         				+ '<ul><li><div style="display: inline-block; margin-right: 9px;">' + user_call_name + '<span>' + timeStr + '</span></div></li></ul>'
         				+ '</dt><dd>' + escape(content) +'</dd></dl>';

         jQuery("#chat_editor").val('');
         jQuery("#chat_content").append(message);
         var chat_content = jQuery("#chat_content")[0];
         chat_content.scrollTop = chat_content.scrollHeight;
     } else {
         alert("请先登录！");
     }
}
//获取联系人
function getContacts() {
	if (connected) {
		var iq = $iq({
			type: 'get',
			id: 'query1'
		}).c('list', {xmlns: 'urn:xmpp:archive'}).c('set', {xmlns: 'http://jabber.org/protocol/rsm'});
		connection.sendIQ(iq, showContactsCards);
	} else {
		alert("请先登录");
	}
}

/*获取聊天记录的总条数*/
function getMsgCount(contacts_name) {
	if (connected) {
		var iq = $iq({
			type: 'get',
			id: 'query3'
		}).c('retrieve', {xmlns: 'urn:xmpp:archive', with: contacts_name + "@" + tigase_domain})
				.c("set", {xmlns: 'http://jabber.org/protocol/rsm'}).c("max", null, 1);
		connection.sendIQ(iq, getMsgCountCallBack);
	}
}

/*获取聊天记录的总条数回调函数*/
function getMsgCountCallBack(iq) {
	var set = iq.firstChild.lastChild;
	if (set !== null) {
		totalCount = set.lastChild.innerHTML;
	} else {
		totalCount = 0;
	}
	checkMoreIndex = totalCount;
	var contacts_name = iq.firstChild.getAttribute("with");
	getHistoryMessageByContacts(contacts_name, totalCount);
}

//获取历史消息
function getHistoryMessageByContacts(contacts_name, totalCount) {
	if (connected) {
		var iq = $iq({
			type: 'get',
			id: 'query2'
		}).c('retrieve', {xmlns: 'urn:xmpp:archive', 'with': contacts_name})
			.c('set', {xmlns: 'http://jabber.org/protocol/rsm'})
			.c("before", null, totalCount)
			.c("max", null, 10)
			.up().c("max", null, 10);
		connection.sendIQ(iq, showHistoryMessage);
	} else {
		alert("请先登录");
	}
}

//搜索联系人
function contactsSearch() {
	var search_name = jQuery.trim(jQuery("#contacts_search").val());
	var obj = jQuery(".chat_left_content dl[contacts_call_name*=" + search_name + "]");
	if (obj.length > 0) {
		obj.css("display", "");
		jQuery(".chat_left_content dl:not([contacts_call_name*=" + search_name + "])").css("display", "none");
		obj[0].click();
	} else {
		alert("不存在该联系人！");
	}
}

/*点击查看更多*/
function checkMore() {
	if (connected) {
		checkMoreCount++;
		if (checkMoreIndex - 10 >= 0) {
			checkMoreIndex = checkMoreIndex - 10;
		} else {
			return false;
		}
		var iq = $iq({
			type: 'get',
			id: 'query5'
		}).c('retrieve', {xmlns: 'urn:xmpp:archive', with: contacts_name + "@" + tigase_domain})
				.c("set", {xmlns: 'http://jabber.org/protocol/rsm'})
				.c("before", null, checkMoreIndex)
				.c("max", null, 10)
				.up().c("max", null, 10);
		connection.sendIQ(iq, getCheckMore);
	} else {
		alert("请先登录");
	}
}

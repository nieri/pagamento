var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#table").show();
    }
    else {
        $("#table").hide();
    }
    $("#transacoes").html("");
}

function connect() {
    var socket = new SockJS('/transacoes-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/transacoes', function (transacoes) {
            showMessage(transacoes.body);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
    stompClient.send("/app/transacoes", {}, 
        JSON.stringify({'action': $("#action").val(), 'cardNumber': $("#cardnumber").val(), 'amount': $("#amount").val()}));
}

function showMessage(message) {
    $("#transacoes").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendMessage(); });
});

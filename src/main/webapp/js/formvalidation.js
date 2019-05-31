var form = document.getElementsByTagName("form")[0];

var email = document.getElementById("input-email");
var pw = document.getElementById("input-pw");
var confpw = document.getElementById("input-conf-pw");

var emailerror = document.getElementsByClassName("email-error")[0];
var pwerror = document.getElementsByClassName("pw-error")[0];
var confpwerror = document.getElementsByClassName("conf-pw-error")[0];

var emailRegExp = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

email.addEventListener("input", function () {
    var test = email.value.length > 0 && emailRegExp.test(email.value);

    if (test) {
        email.className += " valid";
        emailerror.innerHTML = "";
    } else {
        email.className += " invalid";
		emailerror.innerHTML = "<i class='fas fa fa-exclamation-circle'></i>"+" Please insert an e-mail address";
        emailerror.className = "error";
    }
});

pw.addEventListener("input", function () {
    var test = pw.value.length >= 5;

    if (test) {
        pw.className += " valid";
        pwerror.innerHTML = "";
    } else {
        pw.className += " invalid";
		pwerror.innerHTML = "<i class='fas fa fa-exclamation-circle'></i>"+" Password must be at least 5 characters long";
        pwerror.className = "error";
    }
});


form.addEventListener("submit", function (event) {
    var test = email.value.length > 0 && emailRegExp.test(email.value);
    if (test) {
        email.className += " valid";
        emailerror.innerHTML = "";
        emailerror.className = "error";
    } else {
        email.className += " invalid";
        emailerror.innerHTML = "<i class='fas fa fa-exclamation-circle'></i>"+" Please insert a valid e-mail address";
        emailerror.className = "error active";
        event.preventDefault();
    }
	
	var test = pw.value.length >= 5;
    if (test) {
        pw.className += " valid";
        pwerror.innerHTML = "";
        pwerror.className = "error";
    } else {
        pw.className += " invalid";
        pwerror.innerHTML = "<i class='fas fa fa-exclamation-circle'></i>"+" Password is too short (min 5 characters)";
        pwerror.className = "error active";
        event.preventDefault();
    }
	
	var test = pw.value == confpw.value;
    if (test) {
        confpw.className += " valid";
        confpwerror.innerHTML = "";
        confpwerror.className = "error";
    } else {
        confpw.className += " invalid";
        confpwerror.innerHTML = "<i class='fas fa fa-exclamation-circle'></i>"+" Passwords don't match. Try again.";
        confpwerror.className = "error active";
        event.preventDefault();
    }
});

$("#user-img").click(function () {
    $("#input-email").trigger("select");
});

$("#pw-img").click(function () {
    $("#input-pw").trigger("select");
});

$("#conf-pw-img").click(function () {
    $("#input-conf-pw").trigger("select");
});



var userIdentifierId = "#{loginView.authenticatedUser.identifierId}";
function showNotificationPanel() {
    PF('notificationDialog').show();
    document.getElementById('notificationSound').play();

}


let isDialogOpen = false;

// Retrieve the host from systemConfigurationsController
var host = "#{systemConfigurationsController.host}";
var con = "#{systemConfigurationsController.ssh}"
var ws = null;
if(con === '1'){
    ws = new WebSocket("wss://" + host + "/savvy/notifications");
}else if (con === null || con === undefined || con.trim() === '') {
    console.error("Configuration 'ssh' is null, undefined, or empty. Defaulting to ws://");
    ws = new WebSocket("ws://" + host + "/savvy/notifications");
} else {
    ws = new WebSocket("ws://" + host + "/savvy/notifications");
}
ws.onopen = function() {
    console.log("WebSocket connection opened for notification");
};
var notificationListOpen = false;
ws.onmessage = function (event) {
    var message = event.data;

    if (!notificationListOpen) {
        if (message === userIdentifierId) {
            console.log("Notification received for user: " + userIdentifierId);
            showDialog();

        }
    }else{
        //Update dialog content
        notificationPopup();
    }
};


let isLattestNotificationFound = false;
function showDialog() {

    if (isDialogOpen){
        updateDialogData();
        if (isLattestNotificationFound) {
            playNotificationSound();
            isLattestNotificationFound = false; // Reset after handling
        }

    }else{
        const dialog = document.getElementById('notificationDialog');

        // Display the dialog and save its visibility state in local storage
        dialog.style.display = 'block';
        dialog.classList.add('show');
        notificationPopup(); // Your function to load notification content
        playNotificationSound();
        console.log("Notification Dialog POPs");

        // Set visibility state in local storage to persist across refreshes
        localStorage.setItem('notificationDialogVisible', 'true');
        isDialogOpen = true;
    }
}

function showDialog1() {
    if (isDialogOpen)
        updateDialogDate();
    return;
    const dialog = document.getElementById('notificationDialog1');

    // Display the dialog and save its visibility state in local storage
    dialog.style.display = 'block';
    dialog.classList.add('show');
    notificationPopup(); // Your function to load notification content
    playNotificationSound();
    console.log("Notification Dialog POPs for notification 1");

    // Set visibility state in local storage to persist across refreshes
    localStorage.setItem('notificationDialogVisible', 'true');
    isDialogOpen = true;
}

function closeDialog() {
    const dialog = document.getElementById('notificationDialog');
    // Remove 'show' class to trigger closing animation
    dialog.classList.remove('show');

    // Hide dialog after animation and update local storage
    setTimeout(() => {
        dialog.style.display = 'none';
        localStorage.setItem('notificationDialogVisible', 'false');
    }, 500); // Adjust delay to match your animation duration

    //Run a remote command to clear the notification popup list


}

function closeDialog1() {
    const dialog = document.getElementById('notificationDialog1');
}


// Check local storage on page load to determine initial dialog visibility
window.addEventListener('load', function() {
    const isVisible = localStorage.getItem('notificationDialogVisible');

    // Show the dialog if it was previously visible
    if (isVisible === 'true') {
        showDialog();
    }
});

function playNotificationSound() {
    var audio = new Audio("http://" + host + "/savvy/resources/sound/level-up-191997.mp3");
    audio.play().then(() => {
        console.log("Notification Sound Played");
    }).catch(error => {
        console.error("Error playing audio:", error);
    });
}

function isNotificationListOpen() {
    return document.getElementById("notificationList").classList.contains("open");
}

function toggleNotifications() {
    var notificationList = document.getElementById("notificationList");
    notificationList.classList.toggle("open");
    notificationListOpen = notificationList.classList.contains("open");
    if (notificationListOpen) {
        // closeDialog(); // Close the reminder dialog if the notification list is opened
        // console.log("Notification Dialog Close");
    }
}


//Drag Functionality

let isDragging = false;
let offsetX, offsetY;

function startDrag(event) {
    const dialog = document.getElementById("notificationDialog");

    isDragging = true;
    offsetX = event.clientX - dialog.offsetLeft;
    offsetY = event.clientY - dialog.offsetTop;

    document.addEventListener("mousemove", drag);
    document.addEventListener("mouseup", stopDrag);
}

function drag(event) {
    if (isDragging) {
        const dialog = document.getElementById("notificationDialog");

        dialog.style.position = "absolute";
        dialog.style.left = (event.clientX - offsetX) + "px";
        dialog.style.top = (event.clientY - offsetY) + "px";
    }
}

function stopDrag() {
    isDragging = false;
    document.removeEventListener("mousemove", drag);
    document.removeEventListener("mouseup", stopDrag);
}

let lastActivity = new Date().getTime();
let pollStopped = false;

// Reset lastActivity timestamp on user actions
function resetActivity() {
    lastActivity = new Date().getTime();
    if (pollStopped) {
        PF('pollWidget').start(); // Start polling again if it was stopped
        console.log("Poll Started after user interaction");
        pollStopped = false;
    }
}

// Track user interactions
window.onclick = resetActivity;
window.onkeypress = resetActivity;
window.onmousemove = resetActivity;

function checkClientActivity() {
    const now = new Date().getTime();
    const inactivityLimit = 10 * 60 * 1000; // 10 minutes

    if (now - lastActivity > inactivityLimit) {
        PF('pollWidget').stop(); // Stop polling
        console.log("Poll stopped due to user inactivity");
        pollStopped = true;
    }
}

function showNotificationPanel1() {
    var list = document.getElementById('notificationList');
    if (list) {
        list.style.display = 'block';
        console.log("Notification panel shown.");
    } else {
        console.error("Element with ID 'notificationList' not found for showing.");
    }
}

function closeNotificationPanel() {
    var list = document.getElementById('notificationList');
    if (list) {
        list.style.display = 'none';
        console.log("Notification panel hidden.");
    } else {
        console.error("Element with ID 'notificationList' not found for closing.");
    }
}

// Optional: Close dropdown if clicking outside
document.addEventListener('click', function(event) {
    var topbarItem = event.target.closest('.topbar-item');
    var list = document.getElementById('notificationList');
    if (list &amp;&amp; list.style.display === 'block' &amp;&amp; !topbarItem) {
        // Only close if the click is outside the notification item AND the list is visible
        closeNotificationPanel();
    }
}, true); // Use capture phase might help sometimes

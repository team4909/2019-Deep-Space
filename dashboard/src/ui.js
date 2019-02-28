// Set function to be called when robot dis/connects
NetworkTables.addRobotConnectionListener(function onRobotConnection(connected) {
    var state = connected ? 'Robot Connected!' : 'Robot Disconnected.';
}, false);

// Key Listeners
NetworkTables.addKeyListener('/SmartDashboard/time', (key, secondsLeft) => {
    // This is an example of how a dashboard could display the remaining time in a match.
    // We assume here that value is an integer representing the number of seconds left.
    ui.timer.innerHTML = value < 0 ? '0:00' : Math.floor(value / 60) + ':' + (value % 60 < 10 ? '0' : '') + value % 60;
});
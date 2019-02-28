function runCommand(commandName) {
    NetworkTables.putValue('/SmartDashboard/' + commandName + '/running', true);
}
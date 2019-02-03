qx.Class.define("operator.Button",
{
  extend : qx.ui.form.Button,

  construct : function(label, command)
  {
    let             button;

    this.base(arguments, label);
    this.set(
      {
        minWidth  : 80,
        minHeight : 80
      });

    // Prepare to execute the given command when the button is pressed
    this.addCommandListener(command);
  },

  members :
  {
    /**
     * When the button is pressed or tapped, issue the given command to the
     * robot.
     *
     * @param command {String}
     *   The command to issue to the robot
     */
    addCommandListener : function(command)
    {
      this.addListener(
        "execute",
        () => this.issueCommand(command),
        this);
    },

    /**
     * Send the requested command to the robot to be executed.
     *
     * @param command {String}
     *   The command to be executed
     */
    issueCommand : function(command)
    {
      let             req;

      // Prepare the request
      req = new qx.io.request.Xhr("http://localhost:8000/" + command, "GET");
      
      // Arrange to show us the status upon completion
      req.addListener(
        "load",
        (e) =>
          {
            console.log(
              `Request to command ${command}, status ${req.getStatus()}`);
          });

      // Send the request
      req.send();
    }
  }
});

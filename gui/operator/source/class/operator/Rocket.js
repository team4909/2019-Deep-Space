qx.Class.define("operator.Rocket",
{
  extend : qx.ui.container.Composite,

  construct : function()
  {
    let             label;
    let             butLeftHatch3;
    let             butLeftHatch2;
    let             butLeftHatch1;
    let             butCargo3;
    let             butCargo2;
    let             butCargo1;
    let             butRightHatch3;
    let             butRightHatch2;
    let             butRightHatch1;

    // Use a Canvas layout
    this.base(arguments, new qx.ui.layout.Canvas());

    // Add the label
    label = new qx.ui.basic.Label("Rocket Ship");
    label.setFont("headline");
    this.add(label, { left : 74, top : 20 });

    //
    // Add the buttons
    //

    // Left-side hatch covers
    butLeftHatch3 = new operator.Button("Hatch 3");
    this.add(butLeftHatch3, { left : 0, top : 70 });
    
    butLeftHatch2 = new operator.Button("Hatch 2");
    this.add(butLeftHatch2, { left : 0, top : 170 });
    
    butLeftHatch1 = new operator.Button("Hatch 1");
    this.add(butLeftHatch1, { left : 0, top : 270 });
    

    // Cargo
    butCargo3 = new operator.Button("Cargo 3");
    this.add(butCargo3, { left : 90, top : 50 });
    
    butCargo2 = new operator.Button("Cargo 2");
    this.add(butCargo2, { left : 90, top : 150 });
    
    butCargo1 = new operator.Button("Cargo 1");
    this.add(butCargo1, { left : 90, top : 250 });
    

    // Right-side hatch covers
    butRightHatch3 = new operator.Button("Hatch 3");
    this.add(butRightHatch3, { left : 180, top : 70 });
    
    butRightHatch2 = new operator.Button("Hatch 2");
    this.add(butRightHatch2, { left : 180, top : 170 });
    
    butRightHatch1 = new operator.Button("Hatch 1");
    this.add(butRightHatch1, { left : 180, top : 270 });
    butRightHatch1.addListener(
      "execute",
      () => this.issueCommand("rocketHatch1"),
      this);
  },

  members :
  {
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

qx.Class.define("operator.Ship",
{
  extend : qx.ui.container.Composite,

  construct : function()
  {
    let             label;
    let             butHatch;
    let             butCargo;

    // Use a Canvas layout
    this.base(arguments, new qx.ui.layout.Canvas());

    // Add the label
    label = new qx.ui.basic.Label("Cargo Ship");
    label.setFont("headline");
    this.add(label, { left : 0, top : 20 });

    //
    // Add the buttons
    //

    butHatch = new operator.Button("Cargo", "shipCargo");
    this.add(butHatch, { left : 10, top : 170 });
    
    butHatch = new operator.Button("Hatch", "shipHatch");
    this.add(butHatch, { left : 10, top : 270 });
  }
});

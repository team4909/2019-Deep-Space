qx.Class.define("operator.LoadingStation",
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
    label = new qx.ui.basic.Label("Loading Station");
    label.setFont("headline");
    this.add(label, { left : 0, top : 20 });

    //
    // Add the buttons
    //

    butHatch = new operator.Button("Cargo", "loadStationCargo");
    this.add(butHatch, { left : 30, top : 170 });
    
    butHatch = new operator.Button("Hatch", "loadStationHatch");
    this.add(butHatch, { left : 30, top : 270 });
  }
});

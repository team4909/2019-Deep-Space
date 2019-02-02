/**
 * @asset(operator/*)
 */
qx.Class.define("operator.Application",
{
  extend : qx.application.Standalone,

  members :
  {
    main : function()
    {
      let             doc;
      let             tabview;
      let             tabNormalOps;
      let             tabSemiAuto;
      let             rocket;
      let             ship;
      let             loadingStation;

      // Call super class
      this.base(arguments);

      // Enable logging in debug variant
      if (qx.core.Environment.get("qx.debug"))
      {
        let             appender;

        // Mention names of appenders so they are included in the build
        appender = qx.log.appender.Native;
        appender = qx.log.appender.Console;
      }

      // Get the root document
      doc = this.getRoot();

      // Create the tabview for the normal autonomous operations, and the
      // only-semi-autonomous operations modes
      tabview = new qx.ui.tabview.TabView();
      doc.add(tabview, { edge : 10 });

      // Add the Normal Operations tab
      tabNormalOps = new qx.ui.tabview.Page("Normal Operations");
      tabNormalOps.setLayout(new qx.ui.layout.HBox(100));
      tabview.add(tabNormalOps);


      // Add the Semi-Autonomous tab
      tabSemiAuto = new qx.ui.tabview.Page("Semi-Autonomous");
      tabSemiAuto.setLayout(new qx.ui.layout.VBox());
      tabview.add(tabSemiAuto);

      // Create the Rocket
      rocket = new operator.Rocket();
      tabNormalOps.add(rocket);

      // Create the Cargo Ship
      ship = new operator.Ship();
      tabNormalOps.add(ship);

      // Create the Loading Station
      loadingStation = new operator.LoadingStation();
      tabNormalOps.add(loadingStation);
    }
  }
});

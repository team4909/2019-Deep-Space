qx.Class.define("operator.Button",
{
  extend : qx.ui.form.Button,

  construct : function(label)
  {
    let             button;

    this.base(arguments, label);
    this.set(
      {
        minWidth  : 80,
        minHeight : 80
      });
  }
});

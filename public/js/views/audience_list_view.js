
var AudienceListView = Backbone.Collection.extend({
	
});

//Column definitions
var audienceListColumns = [{
  name: "audienceName",
  label: "Audience Name",
  editable: false,
  // The cell type can be a reference of a Backgrid.Cell subclass, any Backgrid.Cell subclass instances like *id* above, or a string
  cell: "string" // This is converted to "StringCell" and a corresponding class in the Backgrid package namespace is looked up
}, {
  name: "pageViews",
  label: "Page Views",
  editable: false,
  cell: "string" // An integer cell is a number cell that displays humanized integers
}, {
  name: "audienceTargetingCode",
  label: "Targeting Code",
  editable: false,
  cell: "string" // A cell type for floating point value, defaults to have a precision 2 decimal numbers
}, {
  name: "uniques",
  label: "Uniques",
  editable: false,
  cell: "string",
}];

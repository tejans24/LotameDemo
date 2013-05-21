
/*
 * Could have added router and other page specific functionality here
 * 
 */

$(document).ready(function(){
	
	var audienceList = new AudienceList();

	// Initialize a new Grid instance
	var grid = new Backgrid.Grid({
	  columns: audienceListColumns,
	  collection: audienceList,
	});
	
	var filter = new Backgrid.Extension.ClientSideFilter({
		  collection: audienceList,
		  fields: ['audienceName']
	});
	
	var $topAudienceReport = $("#topAudienceReport").append(grid.render().el);
	
	// Render the filter
	$topAudienceReport.prepend(filter.render().$el);

	// Fetch some data
	audienceList.fetch({reset: true});
	
	$('input[name="q"]').popover({title: 'Hint', content: "This can be used to search by name", trigger: "focus"});
});
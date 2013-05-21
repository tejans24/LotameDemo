
var AudienceList = Backbone.Collection.extend({
    model: Audience, 
    url: "/reports/topAudiences"
});
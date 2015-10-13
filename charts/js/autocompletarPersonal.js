var personales = new Bloodhound({
  datumTokenizer: Bloodhound.tokenizers.obj.whitespace('name'),
  queryTokenizer: Bloodhound.tokenizers.whitespace,
  limit: 10,
  prefetch: {
    url: 'autocompletePersonal.php',
    // the json file contains an array of strings, but the Bloodhound
    // suggestion engine expects JavaScript objects so this converts all of
    // those strings
    filter: function(list) {
      return $.map(list, function(personal) { return { name: personal }; });
    }
  }
});
 
// kicks off the loading/processing of `local` and `prefetch`
personales.initialize();
 
// passing in `null` for the `options` arguments will result in the default
// options being used
$('#personal').typeahead(null, {
  name: 'personales',
  displayKey: 'name',
  // `ttAdapter` wraps the suggestion engine in an adapter that
  // is compatible with the typeahead jQuery plugin
  source: personales.ttAdapter()
});
/******
 * Author: Ji He
 */


/*
* This functions dislpays loading bar by casting a shadow over the screen.
*/

var LoaderGraphic = LoaderGraphic || {};

LoaderGraphic.ImagePath = "WebResources/loading.gif";

LoaderGraphic.load = function(){
	$('html').append('<div id="JLoaderDisplayer"><img class="loaderimage" src="'+LoaderGraphic.ImagePath+'" /></div>');
	$container = $('html #JLoaderDisplayer');
	$container.height(window.innerHeight);
	$container.width(window.innerWidth);
	$container.css('position', 'absolute');
	$container.css('top', '0px');
	$container.css('left', '0px');
	$container.children('.loaderimage').css('position','absolute');
	$container.children('.loaderimage').css('top', ($container.height()/2-50)+'px');
	$container.children('.loaderimage').css('left', ($container.width()/2-50)+'px');
}

LoaderGraphic.unload = function(){
	$('html #JLoaderDisplayer').detach();
}
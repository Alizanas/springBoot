//hacemos referencia al nombre app que le hemos puesto
//Generamos un modulo y le pasamos un parámetro ngRoute que hace referencia a las direcciones
var app = angular.module('app', ['ngRoute', 'ngSanitize']);

//Configuracion de como va a funcionar mi pagina segun el enlace
app.config(['$routeProvider', '$locationProvider', function ($routeProvider,
  $locationProvider)
  {
//    $locationProvider.html5Mode(true);

    //Si no encuentra la ruta direcciona a /
    $routeProvider.otherwise(
    {
      redirectTo: '/'
    })
    .when('/',
    {
      templateUrl: GBL_COFG.urlTemplate('ini.html')
    })
    .when('/about',
    {
      templateUrl: GBL_COFG.urlTemplate('about.html'),
      controller: 'ngAppControllerAbout'
    })
    .when('/search',
    {
      templateUrl: GBL_COFG.urlTemplate('search.html'),
      controller: 'ngAppControllerSearch'
    })
    .when('/test-bbdd',
    {
      templateUrl: GBL_COFG.urlTemplate('test-bbdd.html'),
      controller: 'ngAppControllerTestBbdd'
    })
    .when('/kirby',
    {
      templateUrl: GBL_COFG.urlTemplate('kirby.html')
    });
  }]);



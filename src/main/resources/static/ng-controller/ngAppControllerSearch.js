app.controller('ngAppControllerSearch',
['$scope', '$http', '$timeout', 'utilFactory',
  function ($scope, $http, $timeout, utilFactory)
  {
    /********************************************************************
     * CONFIG
     *******************************************************************/
    var timer =
    {
      search:
      {
        id: null,
        ms: 750
      }
    };
    
    var msg = 
    {
      el: document.querySelector('#msg > span'),
      style: 
      {
         classnotFound: 'notFound' 
      }
    };
    
    /**************************************************************************
    * 
    * INI
    * 
    * FUNCION AUTOLLAMADA QUE LLAMARÁ A UN CONTROLADOR PARA MOSTRAR TODOS LOS
    * ARTICULOS DEL MENU ANTES DE BUSCARLOS
    * 
    **************************************************************************/

    
    (function ()
    {
      $http.post('/allItem', {})
      .then(function (response)
      {
        scopeItems(response.data);
      });
    })();
    

    /**************************************************************************
    * 
    * FUNCION MODELO
    * 
    * FUNCION QUE ES LLAMADA DESDE EL HTML Y RECIBE UNA CADENA QUE SE BUSCARÁ
    * EN LA BASE DE DATOS
    **************************************************************************/

    
    $scope.searchFn = function (e)
    {
      var value = e.target.value;

      $timeout.cancel(timer.search.id);
      timer.search.id = $timeout(function ()
      {
        utilFactory.setContSearchItems(utilFactory.getContSearchItems() + 1);
        $http.post('/item',
        {
          nombre: value
        })
        .then(function (response)
        {
          scopeItems(response.data);
        });

      }, timer.search.ms);
    };
    
    /**************************************************************************
     * FUNCION PRIVADA
     * 
     * AQUI CONTAMOS EL NUMERO DE DATOS RECIBIDOS Y MOSTRAMOS EL MENSAJE CON EL
     * NUMERO, SI ES 0 MOSTRAMOS EL MENSAJE CON EL NUMERO Y EL ESTILO NOTFOUND
     * 
     **************************************************************************/
    
    function scopeItems(data)
    {
      var length = data.length;
      
      msg.el.classList.remove(msg.style.classnotFound);
      
      if(length > 0)
      {
        $scope.items = data;
      }
      else
      {
        msg.el.classList.add(msg.style.classnotFound);
      }
      
      $scope.msg = data.length;
    }

  }]);
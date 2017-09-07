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
    
    (function ()
    {
    var obj =
    [   
        {
            nombre: 'Alejandro'
        },
        {
           apellidos: 'Lizana'
        }
    ];
    for (var key in obj) 
    {
        console.log(key);   
        for (var key2 in obj[key]) 
        {    
            console.log(key2);
        }   
    }
    })();
    

    /**************************************************************************
    * 
    * FUNCION MODELO
    * 
    * FUNCION QUE ES LLAMADA DESDE EL HTML Y RECIBE UNA CADENA QUE SE BUSCARÁ
    * EN LA BASE DE DATOS
    **************************************************************************/

    //Aquí antes había $http.post(/item),
    $scope.searchFn = function (e)
    {
      var value = e.target.value;
      
      $timeout.cancel(timer.search.id);
      timer.search.id = $timeout(function ()
      {
        utilFactory.setContSearchItems(utilFactory.getContSearchItems() + 1);
        $http.post(isNumeric(value),
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
    
    function isNumeric(data)
    {
        if (data === '')
            return '/allItem';
        else
            return isNaN(data)? '/searchCadena' : '/searchPeso';
    }
    
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
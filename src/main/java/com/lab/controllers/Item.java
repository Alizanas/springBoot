package com.lab.controllers;


import com.lab.persistence.model.ItemModel;
import com.lab.persistence.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;


@Controller
public class Item
{

  @Autowired
  ItemService iService;
  
  @ResponseBody
  @RequestMapping(value = "/searchCadena",
                  method = RequestMethod.POST,
                  produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ItemModel> searchCadena(@RequestBody ItemModel obj) throws Exception
  {
      List<ItemModel> x = iService.searchCadenaItemService(obj);
    return x;
  }
  
  @ResponseBody
  @RequestMapping(value = "/searchPeso",
                  method = RequestMethod.POST,
                  produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ItemModel> searchPeso(@RequestBody ItemModel obj) throws Exception
  {
      List<ItemModel> x = iService.searchPesoItemService(obj);
    return x;
  }
  
  @ResponseBody
  @RequestMapping(value = "/allItem",
                  method = RequestMethod.POST,
                  produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ItemModel> allItem(@RequestBody ItemModel obj) throws Exception
  {
      List<ItemModel> x = iService.allItemService(obj);
    return x;
  }

}

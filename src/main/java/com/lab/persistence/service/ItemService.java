package com.lab.persistence.service;

import com.lab.persistence.model.ItemModel;
import java.util.List;


public interface ItemService
{

  public List<ItemModel> searchCadenaItemService(ItemModel obj) throws Exception;
  
  public List<ItemModel> searchPesoItemService(ItemModel obj) throws Exception;

  public List<ItemModel> allItemService(ItemModel obj) throws Exception;
}

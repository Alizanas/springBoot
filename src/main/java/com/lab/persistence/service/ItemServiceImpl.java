package com.lab.persistence.service;

import com.lab.persistence.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lab.persistence.model.ItemModel;
import java.util.List;
import java.util.regex.Pattern;


@Service
public class ItemServiceImpl implements ItemService
{

  @Autowired
  ItemMapper iMapper;

    @Override
    public List<ItemModel> searchCadenaItemService(ItemModel obj) throws Exception
    {
      List<ItemModel> x = iMapper.ItemMapper(obj);

      List<ItemModel> a = this.replace(obj, x);
      
      return a;
    }
    
    @Override
    public List<ItemModel> searchPesoItemService(ItemModel obj) throws Exception
    {
      List<ItemModel> x = iMapper.pesoItemMapper(obj);

      return x;
    }

    @Override
    public List<ItemModel> allItemService(ItemModel obj) throws Exception
    {
       List<ItemModel> x = iMapper.allItemMapper(obj);
       
       return x;
    }

    private List<ItemModel> replace(ItemModel obj, List<ItemModel> x) 
    {
      for (ItemModel item : x)
      {
          item.setNombre(this.pattern(obj.getNombre()).matcher(item.getNombre()).replaceAll(this.patternReplace(obj.getNombre())));
          item.setDescripcion(this.pattern(obj.getDescripcion()).matcher(item.getDescripcion()).replaceAll(this.patternReplace(obj.getDescripcion())));
      }
//        for (int i =0; i< x.size(); i++)
//        {
//            x.get(i).setNombre(x.get(i).getNombre().replace(obj.getNombre(), "<span class=\"resaltado\">" + obj.getNombre() + "</span>"));
//            x.get(i).setDescripcion(x.get(i).getDescripcion().replace(obj.getNombre(), "<span class=\"resaltado\">" + obj.getNombre() + "</span>"));
//        }
        return x;
    }
    /**
   * METODO PARA GENERAR UN PATRON.
   *
   * @param str Cadena a insertar en el patron de busqueda.
   * @return Patron generado.
   */
  private Pattern pattern(String str)
  {
    return Pattern.compile("(?i)" + str);
  }

  /**
   * METODO PARA REALIZAR UN REMPLAZO DE UNA CADENA POR UN TAG HTML.
   *
   * @param str Cadena a insertar en el tag.
   * @return tag generado en html.
   */
  private String patternReplace(String str)
  {
    return "<span class=\"resaltado\">" + str + "</span>";
  }
    

}

package com.lab.persistence.mapper;

import com.lab.persistence.mapper.*;
import com.lab.persistence.mapper.bbdd.BBDD;
import com.lab.persistence.mapper.bbdd.BBDD;
import com.lab.persistence.model.ItemModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class ItemMapperImpl implements ItemMapper
{

  @Autowired
  BBDD db;


  /**
   * Para no tener que repetir el trozo comentado al final del archivo
   * realizamos una consulta que pasaremos como parametro a un metodo que hace
   * la conexion y nos resuelve el resultado como una lista
   */
  
  @Override
  public List<ItemModel> ItemMapper(ItemModel obj) throws Exception
  {
      return this.addItemModel("SELECT * FROM items WHERE (nombre like '%" + obj.getNombre() + "%')");
  }
  
  @Override
  public List<ItemModel> allItemMapper(ItemModel obj) throws Exception
  {
      return this.addItemModel("SELECT * FROM items");
  }
  
  /**
   * GENERA UNA LISTA DE OBJETOS TIPO ITEMSMODEL.
   *
   * @param String Sql a ejecutar que recibe de las consultas anteriores.
   *
   * @return Lista de objetos tipo ItemsModel.
   */
  
  private List<ItemModel> addItemModel(String sql) throws Exception
  {
    List<ItemModel> listItems = new ArrayList<>();

    db.conecta();

    ResultSet rs = db.consulta(sql);
    while (rs.next())
    {
      ItemModel item = new ItemModel();

      item.setId(rs.getInt("id"));
      item.setNombre(rs.getString("nombre"));
      item.setDescripcion(rs.getString("descripcion"));
      item.setUrl(rs.getString("url"));

      listItems.add(item);
    }

    db.desconecta();

    return listItems;
  }
}         
          
          
          
          
          
          
//  @Override
//  public List<ItemModel> ItemMapper(ItemModel obj) throws Exception
//  {
//      
//    //System.out.println("\n\rPARAMETRO RECIBIDO: " + obj.getTable());
//
//    List<ItemModel> x = new ArrayList<>();
//
//    /**
//     * CONECTANDO A LA BBDD.
//     */
//    db.conecta();
//
//    
//    /**
//     * CONSULTANDO LA CADENA DEL BUSCADOR EN LA TABLA items.
//     */
//    String sql = "SELECT * FROM items WHERE (nombre like '%" + obj.getNombre() + "%')";
//
//    //Metemos los datos de la consulta en rs con resultset
//    ResultSet rs = db.consulta(sql);
//    while (rs.next())
//    {
//        ItemModel item = new ItemModel();
//        
//        item.setId(rs.getInt("id"));
//        item.setNombre(rs.getString("nombre"));
//        item.setDescripcion(rs.getString("descripcion"));
//        item.setUrl(rs.getString("url"));
//      
//         x.add(item);
//    }
//    
//    /**
//     * DESCONECTANDO A LA BBDD.
//     */
//    db.desconecta();
//
//    return x;
//  }



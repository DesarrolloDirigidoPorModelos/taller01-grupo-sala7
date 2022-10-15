package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Municipio 
{

    //------------------------
    // MEMBER VARIABLES
    //------------------------
  
    //Municipio Attributes
    private String razonSocial;
  
    //Municipio Associations
    private List<Cliente> clientes;
  
    //------------------------
    // CONSTRUCTOR
    //------------------------
  
    public Municipio(String aRazonSocial)
    {
      razonSocial = aRazonSocial;
      clientes = new ArrayList<Cliente>();
    }
  
    //------------------------
    // INTERFACE
    //------------------------
  
    public boolean setRazonSocial(String aRazonSocial)
    {
      boolean wasSet = false;
      razonSocial = aRazonSocial;
      wasSet = true;
      return wasSet;
    }
  
    public String getRazonSocial()
    {
      return razonSocial;
    }
    /* Code from template association_GetMany */
    public Cliente getCliente(int index)
    {
      Cliente aCliente = clientes.get(index);
      return aCliente;
    }
  
    public List<Cliente> getClientes()
    {
      List<Cliente> newClientes = Collections.unmodifiableList(clientes);
      return newClientes;
    }
  
    public int numberOfClientes()
    {
      int number = clientes.size();
      return number;
    }
  
    public boolean hasClientes()
    {
      boolean has = clientes.size() > 0;
      return has;
    }
  
    public int indexOfCliente(Cliente aCliente)
    {
      int index = clientes.indexOf(aCliente);
      return index;
    }
    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfClientes()
    {
      return 0;
    }
    /* Code from template association_AddManyToOne */
    public Cliente addCliente(String aNombres, String aApellidos, int aDui, String aDireccion)
    {
      return new Cliente(aNombres, aApellidos, aDui, aDireccion, this);
    }
  
    public boolean addCliente(Cliente aCliente)
    {
      boolean wasAdded = false;
      if (clientes.contains(aCliente)) { return false; }
      Municipio existingMunicipio = aCliente.getMunicipio();
      boolean isNewMunicipio = existingMunicipio != null && !this.equals(existingMunicipio);
      if (isNewMunicipio)
      {
        aCliente.setMunicipio(this);
      }
      else
      {
        clientes.add(aCliente);
      }
      wasAdded = true;
      return wasAdded;
    }
  
    public boolean removeCliente(Cliente aCliente)
    {
      boolean wasRemoved = false;
      //Unable to remove aCliente, as it must always have a municipio
      if (!this.equals(aCliente.getMunicipio()))
      {
        clientes.remove(aCliente);
        wasRemoved = true;
      }
      return wasRemoved;
    }
    /* Code from template association_AddIndexControlFunctions */
    public boolean addClienteAt(Cliente aCliente, int index)
    {  
      boolean wasAdded = false;
      if(addCliente(aCliente))
      {
        if(index < 0 ) { index = 0; }
        if(index > numberOfClientes()) { index = numberOfClientes() - 1; }
        clientes.remove(aCliente);
        clientes.add(index, aCliente);
        wasAdded = true;
      }
      return wasAdded;
    }
  
    public boolean addOrMoveClienteAt(Cliente aCliente, int index)
    {
      boolean wasAdded = false;
      if(clientes.contains(aCliente))
      {
        if(index < 0 ) { index = 0; }
        if(index > numberOfClientes()) { index = numberOfClientes() - 1; }
        clientes.remove(aCliente);
        clientes.add(index, aCliente);
        wasAdded = true;
      } 
      else 
      {
        wasAdded = addClienteAt(aCliente, index);
      }
      return wasAdded;
    }
  
    public void delete()
    {
      for(int i=clientes.size(); i > 0; i--)
      {
        Cliente aCliente = clientes.get(i - 1);
        aCliente.delete();
      }
    }
  
  
    public String toString()
    {
      return super.toString() + "["+
              "razonSocial" + ":" + getRazonSocial()+ "]";
    }
  }
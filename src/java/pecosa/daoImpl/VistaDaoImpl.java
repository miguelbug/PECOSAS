/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pecosa.daoImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pecosa.connectionFactory.MyBatisConnectionFactory;
import pecosa.dao.VistaDao;
import pecosa.model.GuardarDistribucion;
import pecosa.model.GuardarProducto;
import pecosa.model.ProductoVista;

/**
 *
 * @author OGPL
 */
public class VistaDaoImpl implements VistaDao {

    private SqlSessionFactory sqlSessionFactory;

    public VistaDaoImpl() {
        sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    }

    public VistaDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<ProductoVista> getProductosVista() {
        List<ProductoVista> lista = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            lista = session.selectList("VistaData.getProdVista");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("ERROR EN EL IMPL GET PV");
        } finally {
            session.close();
        }
        return lista;
    }

    @Override
    public void confirmarProductos_1(GuardarProducto gp) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.insert("VistaData.insert_productos", gp);
            session.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void confirmarProductos_2(GuardarDistribucion gd) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.insert("VistaData.insert_origen", gd);
            session.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public Integer getIdDependencia(String nombre) {
        Integer inte = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            inte = session.selectOne("VistaData.getIdDepe", nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return inte;
    }

    @Override
    public Integer getIdProductoInterno(Date fecha, String bien, Integer codigo) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("fecha", fecha);
        map.put("bien", bien);
        map.put("codigo", codigo);
        Integer inte = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            inte = session.selectOne("VistaData.getIdProdInterno", map);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return inte;
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

}

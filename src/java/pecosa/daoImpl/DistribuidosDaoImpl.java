/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pecosa.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pecosa.connectionFactory.MyBatisConnectionFactory;
import pecosa.dao.DistribuidosDao;
import pecosa.model.ProductosDistribuidos;

/**
 *
 * @author OGPL
 */
public class DistribuidosDaoImpl implements DistribuidosDao {

    private SqlSessionFactory sqlSessionFactory;

    public DistribuidosDaoImpl() {
        sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    }

    public DistribuidosDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<ProductosDistribuidos> getListaDistribuidos() {
        List<ProductosDistribuidos> lista = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            lista = session.selectList("Distribuidos.getProdDistrib");
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
    public void confirmarSBN(String sbn, Integer idNumero) {
        System.out.println("SBN: "+sbn+" "+"Idnumero: "+idNumero);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sbn", sbn);
        map.put("idnumero", idNumero);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.update("Distribuidos.update_sbn", map);
            session.commit();
        } finally {
            session.close();
        }
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

}

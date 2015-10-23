/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pecosa.daoImpl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pecosa.connectionFactory.MyBatisConnectionFactory;
import pecosa.dao.ListasGeneralesDao;
import pecosa.model.Dependencia;
import pecosa.model.Pecosas;
import pecosa.model.Persona;

/**
 *
 * @author OGPL
 */
public class ListasGeneralesDaoImpl implements ListasGeneralesDao {

    private SqlSessionFactory sqlSessionFactory;

    public ListasGeneralesDaoImpl() {
        sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    }

    public ListasGeneralesDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public boolean validarPecosa(String pecosa) {
        boolean esta = false;
        String peco = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            peco = session.selectOne("ListasGenerales.validarPecosa", pecosa);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL GET PV");
        } finally {
            session.close();
        }
        if(peco==null){
            esta = false;
        }else{
            esta = true;
        }
        return esta;
    }

    @Override
    public List<Dependencia> getListaDependencias() {
        List<Dependencia> lista = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            lista = session.selectList("ListasGenerales.getDependencias");
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
    public List<String> getCodigoPecosas() {
        List<String> lista = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            lista = session.selectList("ListasGenerales.getPecosas");
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
    public List<String> getNombrePersonas(Integer codigo) {
        List<String> lista = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            lista = session.selectList("ListasGenerales.getPersonas", codigo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("ERROR EN EL IMPL GET PV");
        } finally {
            session.close();
        }
        return lista;
    }

}

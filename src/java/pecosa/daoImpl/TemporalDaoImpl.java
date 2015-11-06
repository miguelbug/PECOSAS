/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pecosa.daoImpl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pecosa.connectionFactory.MyBatisConnectionFactory;
import pecosa.dao.TemporalDao;
import pecosa.model.Temporal;

/**
 *
 * @author OGPL
 */
public class TemporalDaoImpl implements TemporalDao {

    private SqlSessionFactory sqlSessionFactory;

    public TemporalDaoImpl() {
        sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    }

    public TemporalDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void actualizarTemporal() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.update("VistaData.actualizar_temporal");
            session.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public Integer getLote() {
        Integer idlote = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            idlote = session.selectOne("VistaData.getLote");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN Confirmados.getLote");
        } finally {
            session.close();
        }
        return idlote;
    }

    @Override
    public void guardarTemporal(Temporal t) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.insert("VistaData.insert_temporal", t);
            session.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }

}

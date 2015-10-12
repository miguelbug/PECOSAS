/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pecosa.daoImpl;

import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pecosa.connectionFactory.MyBatisConnectionFactory;
import pecosa.dao.LoginDao;
import pecosa.model.Usuario;

/**
 *
 * @author OGPL
 */
public class LoginDaoImpl implements LoginDao {

    private SqlSessionFactory sqlSessionFactory;

    public LoginDaoImpl() {
        sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    }

    public LoginDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public Usuario getUsuario(String usu, String clave) {
        Usuario usua = null;
        Map<String,String> map= new HashMap<String,String>();
        map.put("usuario", usu);
        map.put("clave", clave);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            usua = session.selectOne("UsuarioData.getUsuario", map);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL GET USUARIO");
        } finally {
            session.close();
        }
        return usua;
    }

}

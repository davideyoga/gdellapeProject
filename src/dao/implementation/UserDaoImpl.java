package dao.implementation;

import dao.data.DaoData;
import dao.data.DaoDataMySQLImpl;
import dao.exception.DaoException;
import dao.interfaces.UserDao;
import model.User;


import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dao.security.DaoSecurity.stripSlashes;

public class UserDaoImpl extends DaoDataMySQLImpl implements UserDao{

    private PreparedStatement insertUser,
                              selectUserById,
                              selectUserByEmailPassword,
                              updateUser,
                              deleteUserById;




    public UserDaoImpl(DataSource datasource) {
        super(datasource); //richiama il costruttore passandogli un pool della connessione
    }

    /**
     * Crea il collegamento al database e inizializza le query
     * @throws DaoException
     */
    @Override
    public void init() throws DaoException {
        try {
            super.init();

            //inizializzazione query
            this.insertUser = connection.prepareStatement("INSERT INTO user" +
                    "                                          VALUES (NULL, ?,?,?,?,?,?,?,?,?)");

            this.selectUserById = connection.prepareStatement("SELECT *" +
                    "                                              FROM user" +
                    "                                               WHEN id=?");

            this.selectUserByEmailPassword = connection.prepareStatement("SELECT *" +
                    "                                                         FROM user" +
                    "                                                          WHERE email=? AND password=?");

            this.updateUser = connection.prepareStatement("UPDATE user" +
                    "                                           SET surname=?" +
                    "                                               name=?" +
                    "                                               email=?" +
                    "                                               number=?" +
                    "                                               curriculum_ita=?" +
                    "                                               curriculum_eng=?" +
                    "                                               receprion_hours_ita=?" +
                    "                                               receprion_hour_eng=?" +
                    "                                               password=?");

            this.deleteUserById = connection.prepareStatement("DELETE FROM user" +
                    "                                               WHERE id=?");


        } catch (SQLException e) {
            throw new DaoException("Error initializing user dao", e);
        }
    }

    @Override
    public User getuser() {
        return new User(this);
    }

    @Override
    public User getUserById(int idUser) throws DaoException {

        User user = this.getuser();

        try {

            this.selectUserById.setInt(1, idUser); // inserisco idUser nella query

            ResultSet rs = this.selectUserById.executeQuery(); // eseguo la query e mi ritorna il risultato rs

            //riempio user con cio che mi torna dalla query toglinedo gli slash
            user.setId( rs.getInt("id"));
            user.setSurname( stripSlashes(rs.getString("surname") ) );
            user.setName(stripSlashes( rs.getString("name") ) );
            user.setEmail( stripSlashes( rs.getString("email") ) );
            user.setNumber( rs.getInt("number") );
            user.setCurriculum_ita( stripSlashes( rs.getString("curriculum_ita") ) );
            user.setCurriculum_eng( stripSlashes( rs.getString("curriculum_eng") ) );
            user.setReceprion_hours_ita( stripSlashes( rs.getString("receprion_hours_ita") ) );
            user.setReceprion_hours_eng( stripSlashes( rs.getString("receprion_hours_eng") ) );

        } catch (SQLException e) {
            throw new DaoException("Error getUserById in user dao", e);
        }

        return user; //ritorno user
    }

    @Override
    public User getUserByEmailPassword(String email, String password) throws DaoException {

        User user = this.getuser();

        try {
            //setto la query
            this.selectUserByEmailPassword.setString(1, email);
            this.selectUserByEmailPassword.setString(2, password);

            ResultSet rs = this.selectUserByEmailPassword.executeQuery();



            if( rs == null || rs.first() ){ // controllo se il risultato e' nullo
                return null; //non esistono user con tale email e password, esce dal metodo

            }else{// nel risultato vi e' almeno una tupla

                //riempio user con cio che mi torna dalla query toglinedo gli slash
                user.setId( rs.getInt("id"));
                user.setSurname( stripSlashes(rs.getString("surname") ) );
                user.setName(stripSlashes( rs.getString("name") ) );
                user.setEmail( stripSlashes( rs.getString("email") ) );
                user.setNumber( rs.getInt("number") );
                user.setCurriculum_ita( stripSlashes( rs.getString("curriculum_ita") ) );
                user.setCurriculum_eng( stripSlashes( rs.getString("curriculum_eng") ) );
                user.setReceprion_hours_ita( stripSlashes( rs.getString("receprion_hours_ita") ) );
                user.setReceprion_hours_eng( stripSlashes( rs.getString("receprion_hours_eng") ) );

            }

        } catch (SQLException e) {
            throw new DaoException("Error getUserByEmailPassword in user dao", e);
        }

        return user;

    }

    @Override
    public void storeUser(User user) {

        if( user.getId() >= 0 ){ //l'user ha gia' un id, quindi e' gia nel database
            //eseguo un update

        }else{ //user e' un nuovo utente
            //useguo una insert
        }

    }

    @Override
    public void deleteUser(User user) {

    }
}
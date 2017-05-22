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

import static dao.security.DaoSecurity.addSlashes;
import static dao.security.DaoSecurity.stripSlashes;

/**
 *  @author Davide Micarelli
 */
public class UserDaoImpl extends DaoDataMySQLImpl implements UserDao{

    private PreparedStatement insertUser,
                              selectUserById,
                              selectUserByEmailPassword,
                              updateUser,
                              deleteUserById;


    /**
     *
     * @param datasource per connettersi al database
     */
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
                    "                                               WHERE id=?");

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
                    "                                               password=?" +
                    "                                           WHERE id=?");

            this.deleteUserById = connection.prepareStatement("DELETE FROM user" +
                    "                                               WHERE id=?");


        } catch (SQLException e) {
            throw new DaoException("Error initializing user dao", e);
        }
    }

    /**
     * Restituisce un utente vuoto
     * @return User vuoto
     */
    @Override
    public User getUser() {
        return new User(this);
    }

    /**
     * Restituisce un utente in base all'id
     * @param idUser id dell'utente da restituire
     * @return User con id = isUser
     * @throws DaoException
     */
    @Override
    public User getUserById(int idUser) throws DaoException {

        User user = this.getUser();

        try {

            this.selectUserById.setInt(1, idUser); // inserisco idUser nella query

            ResultSet rs = this.selectUserById.executeQuery(); // eseguo la query e mi ritorna il risultato rs

            if(rs.next()){
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
                user.setPassword(stripSlashes(rs.getString("password")));
            }else{
                return null;
            }


        } catch (SQLException e) {
            throw new DaoException("Error getUserById in user dao", e);
        }

        return user; //ritorno user
    }

    /**
     * Restitusce user con Email e password passati, null in caso non sia presente nel database
     * @param email dell'utente cercato
     * @param password dell'utente cercato
     * @return User con email e password passati, null se non esiste
     * @throws DaoException
     */
    @Override
    public User getUserByEmailPassword(String email, String password) throws DaoException {

        User user = this.getUser();

        try {
            //setto la query
            this.selectUserByEmailPassword.setString(1, addSlashes(email));
            this.selectUserByEmailPassword.setString(2, addSlashes(password));

            ResultSet rs = this.selectUserByEmailPassword.executeQuery();

            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setSurname(stripSlashes(rs.getString("surname")));
                user.setName(stripSlashes(rs.getString("name")));
                user.setEmail(stripSlashes(rs.getString("email")));
                user.setNumber(rs.getInt("number"));
                user.setCurriculum_ita(stripSlashes(rs.getString("curriculum_ita")));
                user.setCurriculum_eng(stripSlashes(rs.getString("curriculum_eng")));
                user.setReceprion_hours_ita(stripSlashes(rs.getString("receprion_hours_ita")));
                user.setReceprion_hours_eng(stripSlashes(rs.getString("receprion_hours_eng")));
                user.setPassword(rs.getString("password"));


            }else{
                return null;
            }

        } catch (SQLException e) {
            throw new DaoException("Error getUserByEmailPassword in user dao", e);
        }

        return user;

    }

    /**
     * dato un utente, se presenta un id, quindi gia' risulta salvato nel dataabse, viene effettuato un update,
     * altrimenti una insert
     * @param user che si vuole inserire o modificare nel database
     * @throws DaoException
     */
    @Override
    public void storeUser(User user) throws DaoException {

        if( user.getId() > 0 ){ //l'user ha gia' un id, quindi e' gia nel database

            try {

                this.updateUser.setString(1, addSlashes(user.getSurname()));
                this.updateUser.setString(2, addSlashes(user.getName()));
                this.updateUser.setString(3, addSlashes(user.getEmail()));
                this.updateUser.setInt(4, user.getNumber());
                this.updateUser.setString(5, addSlashes(user.getCurriculum_ita()));
                this.updateUser.setString(6, addSlashes(user.getCurriculum_eng()));
                this.updateUser.setString(7, addSlashes(user.getReceprion_hours_ita()));
                this.updateUser.setString(8, addSlashes(user.getReceprion_hours_eng()));
                this.updateUser.setString(9, addSlashes(user.getPassword()));
                this.updateUser.setInt(10, user.getId());

                this.updateUser.executeUpdate();

            } catch (SQLException e) {
                throw new DaoException("Error storeUser in user dao", e);
            }


        }else{ //user e' un nuovo utente
            //eseguo una insert

            try {

                this.insertUser.setString(1, addSlashes(user.getSurname()));
                this.insertUser.setString(2, addSlashes(user.getName()));
                this.insertUser.setString(3, addSlashes(user.getEmail()));
                this.insertUser.setInt(4, user.getNumber());
                this.insertUser.setString(5, addSlashes(user.getCurriculum_ita()));
                this.insertUser.setString(6, addSlashes(user.getCurriculum_eng()));
                this.insertUser.setString(7, addSlashes(user.getReceprion_hours_ita()));
                this.insertUser.setString(8, addSlashes(user.getReceprion_hours_eng()));
                this.insertUser.setString(9, addSlashes(user.getPassword()));

                this.insertUser.executeUpdate();

            } catch (SQLException e) {
                throw new DaoException("Error storeUser in user dao", e);
            }

        }

    }

    /**
     * elimina un utente dal database
     * @param user da eliminare dal database
     * @throws DaoException
     */
    @Override
    public void deleteUser(User user) throws DaoException {

        try {

            this.deleteUserById.setInt(1, user.getId() );

            this.deleteUserById.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Error deleteUser in user dao", e);
        }
    }
}
package dao.implementation;

import dao.data.DaoDataMySQLImpl;
import dao.exception.*;
import dao.interfaces.UserDao;
import model.Groups;
import model.User;


import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dao.security.DaoSecurity.addSlashes;
import static dao.security.DaoSecurity.stripSlashes;

/**
 *  @author Davide Micarelli
 */
public class UserDaoImpl extends DaoDataMySQLImpl implements UserDao{

    private PreparedStatement   insertUser,
                                selectUserById,
                                selectUserByEmailPassword,
                                updateUser,
                                deleteUserById,
                                selectUserByGroupsId;


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
                    "                                           SET surname=?," +
                    "                                               name=?," +
                    "                                               email=?," +
                    "                                               number=?," +
                    "                                               curriculum_ita=?," +
                    "                                               curriculum_eng=?," +
                    "                                               receprion_hours_ita=?," +
                    "                                               receprion_hours_eng=?," +
                    "                                               password=?" +
                    "                                           WHERE id=?");

            this.deleteUserById = connection.prepareStatement("DELETE FROM user" +
                    "                                               WHERE id=?");

            this.selectUserByGroupsId = connection.prepareStatement("SELECT * " +
                    "                                                       FROM user " +
                    "                                                       LEFT JOIN user_groups " +
                    "                                                       ON user.id = user_groups.user_id " +
                    "                                                       WHERE user_groups.groups_id=? ");


        } catch (SQLException e) {
            throw new InitDaoException("Error initializing user dao", e);
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
    public User getUserById( int idUser) throws DaoException {

        User user = null;

        try {

            this.selectUserById.setInt(1, idUser); // inserisco idUser nella query

            ResultSet rs = this.selectUserById.executeQuery(); // eseguo la query e mi ritorna il risultato rs
            if(rs.next()) {
                user = this.generateUser(rs);
            }
        } catch (SQLException e) {
            throw new SelectDaoException("Error getUserById in user dao", e);
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

            if(rs.next()) {
                user = this.generateUser(rs);
            }
        } catch (SQLException e) {
            throw new SelectDaoException("Error getUserByEmailPassword in user dao" + e, e);
        }

        return user;
    }

    /**
     * dato un utente, se presenta un id, quindi gia' risulta salvato nel database, viene effettuato un update,
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
                this.updateUser.setLong(4, user.getNumber());
                this.updateUser.setString(5, addSlashes(user.getCurriculum_ita()));
                this.updateUser.setString(6, addSlashes(user.getCurriculum_eng()));
                this.updateUser.setString(7, addSlashes(user.getReceprion_hours_ita()));
                this.updateUser.setString(8, addSlashes(user.getReceprion_hours_eng()));
                this.updateUser.setString(9, addSlashes(user.getPassword()));
                this.updateUser.setInt(10, user.getId());

                this.updateUser.executeUpdate();

            } catch (SQLException e) {
                throw new UpdateDaoException("Error storeUser in user dao", e);
            }


        }else{ //user e' un nuovo utente
            //eseguo una insert

            try {

                this.insertUser.setString(1, addSlashes(user.getSurname()));
                this.insertUser.setString(2, addSlashes(user.getName()));
                this.insertUser.setString(3, addSlashes(user.getEmail()));
                this.insertUser.setLong(4, user.getNumber());
                this.insertUser.setString(5, addSlashes(user.getCurriculum_ita()));
                this.insertUser.setString(6, addSlashes(user.getCurriculum_eng()));
                this.insertUser.setString(7, addSlashes(user.getReceprion_hours_ita()));
                this.insertUser.setString(8, addSlashes(user.getReceprion_hours_eng()));
                this.insertUser.setString(9, addSlashes(user.getPassword()));

                this.insertUser.executeUpdate();

            } catch (SQLException e) {
                throw new InsertDaoException("Error storeUser in user dao", e);
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
            throw new DeleteDaoException("Error deleteUser in user dao", e);
        }
    }

    /**
     * Gli va passato un Resultset gia' ciclato
     * @param rs
     * @return
     * @throws DaoException
     */
    @Override
    public User generateUser(ResultSet rs) throws DaoException {

        User user = this.getUser();

        try {

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

        } catch (SQLException e) {
            throw new DaoException("Error generateUser", e);
        }

        return user;
    }

    @Override
    public List<User> getUserByGroups(Groups groups) throws DaoException {

        List<User> list = new ArrayList<>();

        try{

            this.selectUserByGroupsId.setInt( 1, groups.getId());

            ResultSet rs = this.selectUserByGroupsId.executeQuery();

            //rs ritorna un insieme di tuple rappresentanti i gruppi a cuoi appartiene l'utente
            //scorro rs ed aggiungo alla lista il gruppo
            while( rs.next() ){

                User user = this.generateUser(rs);

                list.add(user);
            }

        }catch (Exception e) {
            throw new DaoException("Error query getUserByGroups", e);
        }

        return list;
    }

    @Override
    public void destroy() throws DaoException{
        try {

            //chiudo le query
            this.updateUser.close();
            this.insertUser.close();
            this.deleteUserById.close();
            this.selectUserByEmailPassword.close();
            this.selectUserById.close();
            this.selectUserByGroupsId.close();

            super.destroy(); //chiudo la connessione


        } catch (DaoException e) {
            throw new DestroyDaoException("Error destroy in user dao", e);
        } catch (SQLException e) {
            throw new DestroyDaoException("Error destroy in user dao", e);
        }
    }
}
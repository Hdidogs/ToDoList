package fr.hdidogs.todolist;

import java.sql.*;

public class SQLHelper {
    public static Connection connectBDD() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/are_todolist", "root", "");
    }

    public static int addUser(String nom, String prenom, String mail, String mdp) throws SQLException {
        PreparedStatement preparedStatement = connectBDD().prepareStatement("INSERT INTO user (nom, prenom, mail, mdp) VALUES (?, ?, ?, ?)");

        preparedStatement.setString(1, nom);
        preparedStatement.setString(2, prenom);
        preparedStatement.setString(3, mail);
        preparedStatement.setString(4, mdp);

        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        int id_user = resultSet.getInt(1);

        connectBDD().close();

        return id_user;
    }

    public static int addListe(String nom, String date_debut) throws SQLException {
        PreparedStatement preparedStatement = connectBDD().prepareStatement("INSERT INTO liste (nom, date_debut) VALUES (?, ?)");

        preparedStatement.setString(1, nom);
        preparedStatement.setString(2, date_debut);

        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        int id_liste = resultSet.getInt(1);

        connectBDD().close();

        return id_liste;
    }

    public static int addTache(String difficulte, String nom, String desc) throws SQLException {
        PreparedStatement preparedStatement = connectBDD().prepareStatement("INSERT INTO tache (difficulte, nom, description) VALUES (?, ?, ?)");

        preparedStatement.setString(1, difficulte);
        preparedStatement.setString(2, nom);
        preparedStatement.setString(3, desc);

        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        int id_tache = resultSet.getInt(1);

        connectBDD().close();

        return id_tache;
    }

    public static int addType(String desc, String couleur) throws SQLException {
        PreparedStatement preparedStatement = connectBDD().prepareStatement("INSERT INTO tache (description, couleur) VALUES (?, ?)");

        preparedStatement.setString(1, desc);
        preparedStatement.setString(2, couleur);

        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        int id_type = resultSet.getInt(1);

        connectBDD().close();

        return id_type;
    }

    public static ResultSet getAllListeFromUser(int id_user) throws SQLException {
        PreparedStatement preparedStatement = connectBDD().prepareStatement("SELECT ref_liste FROM userliste WHERE ref_user = ?");

        preparedStatement.setInt(1, id_user);

        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;
    }

    public static int[] getAllTacheFromListe(ResultSet id_liste) throws SQLException {
        int i = 0;

        while (id_liste.next()) {
            i = i + 1;
        }

        int[] id_tache = new int[i];

        PreparedStatement preparedStatement = connectBDD().prepareStatement("SELECT id_tache FROM tache WHERE ref_liste = ?");

        i = 0;

        while (id_liste.next()) {
            i = i + 1;

            preparedStatement.setInt(1, id_liste.getInt("id_liste"));

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                id_tache = new int[]{resultSet.getInt("id_tache")};
            }
        }

        return id_tache;
    }
}

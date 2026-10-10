/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Penghuni;

/**
 *
 * @author LENOVO
 */
public class PenghuniDAO {

    private Connection conn;

    public PenghuniDAO() {
        this.conn = Koneksi.getConnection();
    }

    // CREATE
    public void tambahPenghuni(Penghuni penghuni) {

        if (conn == null) {
            System.out.println("Koneksi database tidak tersedia.");
            return;
        }

        String sql =
                "INSERT INTO penghuni "
                + "(nim, nama, asal_daerah, fakultas, "
                + "jurusan, status, keterangan) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, penghuni.getNim());
            pstmt.setString(2, penghuni.getNama());
            pstmt.setString(3, penghuni.getAsalDaerah());
            pstmt.setString(4, penghuni.getFakultas());
            pstmt.setString(5, penghuni.getJurusan());
            pstmt.setString(6, penghuni.getStatus());
            pstmt.setString(7, penghuni.getKeterangan());

            int baris = pstmt.executeUpdate();

            if (baris > 0) {
                System.out.println("Data penghuni berhasil ditambahkan!");
            }

        } catch (SQLException e) {
            System.out.println(
                    "Gagal menambah penghuni - " + e.getMessage()
            );
        }
    }

    // READ: seluruh penghuni
    public List<Penghuni> ambilSemuaPenghuni() {

        List<Penghuni> daftarPenghuni = new ArrayList<>();

        if (conn == null) {
            System.out.println("Koneksi database tidak tersedia.");
            return daftarPenghuni;
        }

        String sql =
                "SELECT nim, nama, asal_daerah, fakultas, "
                + "jurusan, status, keterangan "
                + "FROM penghuni ORDER BY nim";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

                Penghuni penghuni = new Penghuni(
                        rs.getString("nama"),
                        rs.getString("nim"),
                        rs.getString("asal_daerah"),
                        rs.getString("fakultas"),
                        rs.getString("jurusan"),
                        rs.getString("status"),
                        rs.getString("keterangan")
                );

                daftarPenghuni.add(penghuni);
            }

        } catch (SQLException e) {
            System.out.println(
                    "Gagal mengambil data penghuni - " + e.getMessage()
            );
        }

        return daftarPenghuni;
    }

    // READ: satu penghuni berdasarkan NIM
    public Penghuni cariPenghuniBerdasarkanNIM(String nim) {

        if (conn == null) {
            System.out.println("Koneksi database tidak tersedia.");
            return null;
        }

        String sql =
                "SELECT nim, nama, asal_daerah, fakultas, "
                + "jurusan, status, keterangan "
                + "FROM penghuni WHERE nim = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nim);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    return new Penghuni(
                            rs.getString("nama"),
                            rs.getString("nim"),
                            rs.getString("asal_daerah"),
                            rs.getString("fakultas"),
                            rs.getString("jurusan"),
                            rs.getString("status"),
                            rs.getString("keterangan")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println(
                    "Gagal mencari penghuni - " + e.getMessage()
            );
        }

        return null;
    }

    // UPDATE
    public void updatePenghuni(String nim, Penghuni penghuni) {

        if (conn == null) {
            System.out.println("Koneksi database tidak tersedia.");
            return;
        }

        String sql =
                "UPDATE penghuni SET "
                + "nama = ?, asal_daerah = ?, fakultas = ?, "
                + "jurusan = ?, status = ?, keterangan = ? "
                + "WHERE nim = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, penghuni.getNama());
            pstmt.setString(2, penghuni.getAsalDaerah());
            pstmt.setString(3, penghuni.getFakultas());
            pstmt.setString(4, penghuni.getJurusan());
            pstmt.setString(5, penghuni.getStatus());
            pstmt.setString(6, penghuni.getKeterangan());
            pstmt.setString(7, nim);

            int baris = pstmt.executeUpdate();

            if (baris > 0) {
                System.out.println("Data penghuni berhasil diubah!");
            } else {
                System.out.println("NIM penghuni tidak ditemukan.");
            }

        } catch (SQLException e) {
            System.out.println(
                    "Gagal mengubah penghuni - " + e.getMessage()
            );
        }
    }

    // DELETE
    public void hapusPenghuni(String nim) {

        if (conn == null) {
            System.out.println("Koneksi database tidak tersedia.");
            return;
        }

        String sql = "DELETE FROM penghuni WHERE nim = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nim);

            int baris = pstmt.executeUpdate();

            if (baris > 0) {
                System.out.println("Data penghuni berhasil dihapus!");
            } else {
                System.out.println("NIM penghuni tidak ditemukan.");
            }

        } catch (SQLException e) {
            System.out.println(
                    "Gagal menghapus penghuni - " + e.getMessage()
            );
        }
    }
}

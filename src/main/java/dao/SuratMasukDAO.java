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
import model.SuratMasuk;

/**
 *
 * @author LENOVO
 */
public class SuratMasukDAO {

    private Connection conn;

    public SuratMasukDAO() {
        this.conn = Koneksi.getConnection();
    }

    public void tambahSuratMasuk(SuratMasuk surat) {
        if (conn == null) {
            System.out.println("Koneksi database tidak tersedia.");
            return;
        }

        String sqlUrutan =
                "SELECT COALESCE(MAX(urutan_surat), 0) + 1 "
                + "AS urutan_baru FROM surat_masuk";

        String sql =
                "INSERT INTO surat_masuk "
                + "(urutan_surat, nomor_surat, perihal, "
                + "tanggal_masuk, pengirim) "
                + "VALUES (?, ?, ?, STR_TO_DATE(?, '%d-%m-%Y'), ?)";

        try (PreparedStatement pstmtUrutan =
                     conn.prepareStatement(sqlUrutan);
             ResultSet rs = pstmtUrutan.executeQuery()) {

            int urutanBaru = 1;

            if (rs.next()) {
                urutanBaru = rs.getInt("urutan_baru");
            }

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, urutanBaru);
                pstmt.setString(2, surat.getNomorSurat());
                pstmt.setString(3, surat.getPerihal());
                pstmt.setString(4, surat.getTanggalMasukSurat());
                pstmt.setString(5, surat.getPengirim());

                int baris = pstmt.executeUpdate();

                if (baris > 0) {
                    System.out.println("Surat masuk berhasil ditambahkan!");
                }
            }

        } catch (SQLException e) {
            System.out.println(
                    "Gagal menambah surat masuk - " + e.getMessage()
            );
        }
    }

    public List<SuratMasuk> ambilSemuaSuratMasuk() {
        List<SuratMasuk> daftarSuratMasuk = new ArrayList<>();

        if (conn == null) {
            System.out.println("Koneksi database tidak tersedia.");
            return daftarSuratMasuk;
        }

        String sql =
                "SELECT urutan_surat, nomor_surat, perihal, "
                + "DATE_FORMAT(tanggal_masuk, '%d-%m-%Y') "
                + "AS tanggal_masuk, pengirim "
                + "FROM surat_masuk ORDER BY urutan_surat";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int urutanSurat = rs.getInt("urutan_surat");
                String nomorSurat = rs.getString("nomor_surat");
                String perihal = rs.getString("perihal");
                String tanggalMasuk = rs.getString("tanggal_masuk");
                String pengirim = rs.getString("pengirim");

                SuratMasuk surat = new SuratMasuk(
                        urutanSurat,
                        nomorSurat,
                        perihal,
                        tanggalMasuk,
                        pengirim
                );

                daftarSuratMasuk.add(surat);
            }

        } catch (SQLException e) {
            System.out.println(
                    "Gagal mengambil data surat masuk - " + e.getMessage()
            );
        }

        return daftarSuratMasuk;
    }

    public void updateSuratMasuk(int urutanSurat, SuratMasuk surat) {
        if (conn == null) {
            System.out.println("Koneksi database tidak tersedia.");
            return;
        }

        String sql =
                "UPDATE surat_masuk SET nomor_surat=?, perihal=?, "
                + "tanggal_masuk=STR_TO_DATE(?, '%d-%m-%Y'), "
                + "pengirim=? WHERE urutan_surat=?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, surat.getNomorSurat());
            pstmt.setString(2, surat.getPerihal());
            pstmt.setString(3, surat.getTanggalMasukSurat());
            pstmt.setString(4, surat.getPengirim());
            pstmt.setInt(5, urutanSurat);

            int baris = pstmt.executeUpdate();

            if (baris > 0) {
                System.out.println("Data surat masuk berhasil diubah!");
            } else {
                System.out.println("Urutan surat masuk tidak ditemukan.");
            }

        } catch (SQLException e) {
            System.out.println(
                    "Gagal mengubah surat masuk - " + e.getMessage()
            );
        }
    }

    public void hapusSuratMasuk(int urutanSurat) {
        if (conn == null) {
            System.out.println("Koneksi database tidak tersedia.");
            return;
        }

        String sql =
                "DELETE FROM surat_masuk WHERE urutan_surat=?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, urutanSurat);

            int baris = pstmt.executeUpdate();

            if (baris > 0) {
                System.out.println("Surat masuk berhasil dihapus!");
            } else {
                System.out.println("Urutan surat masuk tidak ditemukan.");
            }

        } catch (SQLException e) {
            System.out.println(
                    "Gagal menghapus surat masuk - " + e.getMessage()
            );
        }
    }
}
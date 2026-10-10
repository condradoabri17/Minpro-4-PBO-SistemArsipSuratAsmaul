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
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import model.SuratKeluar;
/**
 *
 * @author LENOVO
 */
public class SuratKeluarDAO {

    private Connection conn;

    public SuratKeluarDAO() {
        this.conn = Koneksi.getConnection();
    }

    public void tambahSuratKeluar(SuratKeluar surat) {
        if (conn == null) {
            System.out.println("Koneksi database tidak tersedia.");
            return;
        }

        String sqlUrutan =
                "SELECT COALESCE(MAX(urutan_surat), 0) + 1 "
                + "AS urutan_baru FROM surat_keluar";

        String sql =
                "INSERT INTO surat_keluar "
                + "(urutan_surat, nomor_surat, perihal, kategori, "
                + "tanggal_keluar, penerima) "
                + "VALUES (?, ?, ?, ?, STR_TO_DATE(?, '%d-%m-%Y'), ?)";

        try (PreparedStatement pstmtUrutan =
                     conn.prepareStatement(sqlUrutan);
             ResultSet rs = pstmtUrutan.executeQuery()) {

            int urutanBaru = 1;

            if (rs.next()) {
                urutanBaru = rs.getInt("urutan_baru");
            }

            int kategori = surat.getKategoriSuratAngka();

            int tahun = Year.now().getValue();

            String nomorSurat = String.format(
                    "%02d/%03d/UKMAsramaPutra/%d",
                    kategori,
                    urutanBaru,
                    tahun
            );

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, urutanBaru);
                pstmt.setString(2, nomorSurat);
                pstmt.setString(3, surat.getPerihal());
                pstmt.setInt(4, kategori);
                pstmt.setString(5, surat.getTanggalKeluarSurat());
                pstmt.setString(6, surat.getPenerima());

                int baris = pstmt.executeUpdate();

                if (baris > 0) {
                    System.out.println(
                            "Surat keluar berhasil ditambahkan!"
                    );
                    System.out.println(
                            "Nomor surat: " + nomorSurat
                    );
                }
            }

        } catch (SQLException | NumberFormatException e) {
            System.out.println(
                    "Gagal menambah surat keluar - " + e.getMessage()
            );
        }
    }

    public List<SuratKeluar> ambilSemuaSuratKeluar() {
        List<SuratKeluar> daftarSuratKeluar = new ArrayList<>();

        if (conn == null) {
            System.out.println("Koneksi database tidak tersedia.");
            return daftarSuratKeluar;
        }

        String sql =
                "SELECT urutan_surat, nomor_surat, perihal, kategori, "
                + "DATE_FORMAT(tanggal_keluar, '%d-%m-%Y') "
                + "AS tanggal_keluar, penerima "
                + "FROM surat_keluar ORDER BY urutan_surat";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int urutanSurat = rs.getInt("urutan_surat");
                String nomorSurat = rs.getString("nomor_surat");
                String perihal = rs.getString("perihal");
                int kategori = rs.getInt("kategori");
                String tanggalKeluar = rs.getString("tanggal_keluar");
                String penerima = rs.getString("penerima");

                SuratKeluar surat = new SuratKeluar(
                        urutanSurat,
                        nomorSurat,
                        perihal,
                        kategori,
                        tanggalKeluar,
                        penerima
                );

                daftarSuratKeluar.add(surat);
            }

        } catch (SQLException e) {
            System.out.println(
                    "Gagal mengambil data surat keluar - "
                    + e.getMessage()
            );
        }

        return daftarSuratKeluar;
    }

    public void updateSuratKeluar(
            int urutanSurat,
            SuratKeluar surat) {

        if (conn == null) {
            System.out.println("Koneksi database tidak tersedia.");
            return;
        }

        String sql =
                "UPDATE surat_keluar SET "
                + "perihal = ?, kategori = ?, "
                + "tanggal_keluar = STR_TO_DATE(?, '%d-%m-%Y'), "
                + "penerima = ? "
                + "WHERE urutan_surat = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int kategori = surat.getKategoriSuratAngka();

            pstmt.setString(1, surat.getPerihal());
            pstmt.setInt(2, kategori);
            pstmt.setString(3, surat.getTanggalKeluarSurat());
            pstmt.setString(4, surat.getPenerima());
            pstmt.setInt(5, urutanSurat);

            int baris = pstmt.executeUpdate();

            if (baris > 0) {
                System.out.println(
                        "Data surat keluar berhasil diubah!"
                );
            } else {
                System.out.println(
                        "Urutan surat keluar tidak ditemukan."
                );
            }

        } catch (SQLException | NumberFormatException e) {
            System.out.println(
                    "Gagal mengubah surat keluar - " + e.getMessage()
            );
        }
    }

    public void hapusSuratKeluar(int urutanSurat) {
        if (conn == null) {
            System.out.println("Koneksi database tidak tersedia.");
            return;
        }

        String sql =
                "DELETE FROM surat_keluar WHERE urutan_surat = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, urutanSurat);

            int baris = pstmt.executeUpdate();

            if (baris > 0) {
                System.out.println(
                        "Surat keluar berhasil dihapus!"
                );
            } else {
                System.out.println(
                        "Urutan surat keluar tidak ditemukan."
                );
            }

        } catch (SQLException e) {
            System.out.println(
                    "Gagal menghapus surat keluar - " + e.getMessage()
            );
        }
    }
}

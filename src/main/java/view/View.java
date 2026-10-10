/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.ArsipController;
import java.util.ArrayList;
import java.util.Scanner;
import model.Penghuni;
import model.SuratKeluar;
import model.SuratMasuk;

/**
 *
 * @author LENOVO
 */
public class View {

    private final Scanner input;
    private final ArsipController controller;

    public View() {
        input = new Scanner(System.in);
        controller = new ArsipController();
    }

    public void jalankanProgram() {

        boolean berjalan = true;

        while (berjalan) {

            tampilkanMenuUtama();

            int pilihan = inputAngka(
                    "Pilih menu: "
            );

            if (pilihan == 1) {

                menuSuratMasuk();

            } else if (pilihan == 2) {

                menuSuratKeluar();

            } else if (pilihan == 3) {

                menuPenghuni();

            } else if (pilihan == 0) {

                berjalan = false;
                System.out.println(
                        "Program selesai."
                );

            } else {

                System.out.println(
                        "Menu tidak tersedia."
                );
            }
        }
    }

    private void tampilkanMenuUtama() {

        System.out.println();
        System.out.println("================================");
        System.out.println(" SISTEM PENGARSIPAN SURAT ASMAUL\n");
        System.out.println("1. Kelola Surat Masuk");
        System.out.println("2. Kelola Surat Keluar");
        System.out.println("3. Kelola Data Penghuni");
        System.out.println("0. Keluar");
        System.out.println("================================");
    }

    private void menuSuratMasuk() {

        boolean kembali = false;

        while (!kembali) {

            System.out.println();
            System.out.println("================================");
            System.out.println("KELOLA SURAT MASUK");
            System.out.println("1. Lihat Surat Masuk");
            System.out.println("2. Tambah Surat Masuk");
            System.out.println("3. Edit Surat Masuk");
            System.out.println("4. Hapus Surat Masuk");
            System.out.println("5. Cari Surat");
            System.out.println("0. Kembali");
            System.out.println("================================");

            int pilihan = inputAngka(
                    "Pilih menu: "
            );

            if (pilihan == 1) {

                tampilkanSuratMasuk();

            } else if (pilihan == 2) {

                tambahSuratMasuk();

            } else if (pilihan == 3) {

                editSuratMasuk();

            } else if (pilihan == 4) {

                hapusSuratMasuk();

            }else if (pilihan == 5) {

                menuPencarianSurat();

            } else if (pilihan == 0) {

                kembali = true;

            } else {

                System.out.println(
                        "Menu tidak tersedia."
                );
            }
        }
    }

    private void tampilkanSuratMasuk() {

        ArrayList<SuratMasuk> daftar =
                controller.getDaftarSuratMasuk();

        if (daftar.isEmpty()) {

            System.out.println(
                    "Belum ada surat masuk."
            );

            return;
        }

        System.out.println();
        System.out.println("SURAT MASUK");

        for (int i = 0; i < daftar.size(); i++) {

            System.out.println(
                    "Data ke-" + (i + 1)
            );

            daftar.get(i).tampilkanDaftarSurat();

            System.out.println();
        }
    }

    private void tambahSuratMasuk() {

        System.out.println();
        System.out.println("TAMBAH SURAT MASUK");
        System.out.println(
                "Contoh nomor: 001/Fakultas-Teknik/2026"
        );
        System.out.println(
                "Contoh tanggal: 08-02-2026"
        );

        String nomorSurat = inputTeks(
                "Nomor Surat: "
        );

        String perihal = inputTeks(
                "Perihal: "
        );

        String tanggal = inputTanggal(
                "Tanggal Masuk: "
        );

        String pengirim = inputTeks(
                "Pengirim: "
        );

        controller.tambahSuratMasuk(
                nomorSurat,
                perihal,
                tanggal,
                pengirim
        );
    }

    private void editSuratMasuk() {

        ArrayList<SuratMasuk> daftar =
                controller.getDaftarSuratMasuk();

        if (daftar.isEmpty()) {

            System.out.println(
                    "Belum ada surat masuk."
            );

            return;
        }

        tampilkanSuratMasuk();

        int nomorData = inputAngka(
                "Pilih nomor data yang ingin diedit: "
        );

        if (nomorData < 1
                || nomorData > daftar.size()) {

            System.out.println(
                    "Nomor data tidak tersedia."
            );

            return;
        }

        SuratMasuk surat =
                daftar.get(nomorData - 1);

        System.out.println();
        System.out.println("Tekan ENTER jika ingin mempertahankan data lama.");

        System.out.println(
                "Nomor lama: "
                + surat.getNomorSurat()
        );

        System.out.println(
                "Contoh nomor baru: 002/Fakultas-Teknik/2026"
        );

        String nomorBaru = input.nextLine();

        System.out.println(
                "Perihal lama: "
                + surat.getPerihal()
        );

        String perihalBaru = input.nextLine();

        System.out.println(
                "Tanggal lama: "
                + surat.getTanggalMasukSurat()
        );

        System.out.println(
                "Contoh tanggal: 08-02-2026"
        );

        String tanggalBaru = input.nextLine();

        System.out.println(
                "Pengirim lama: "
                + surat.getPengirim()
        );

        String pengirimBaru = input.nextLine();

        controller.editSuratMasuk(
                nomorData - 1,
                nomorBaru,
                perihalBaru,
                tanggalBaru,
                pengirimBaru
        );
    }

    private void hapusSuratMasuk() {

        ArrayList<SuratMasuk> daftar =
                controller.getDaftarSuratMasuk();

        if (daftar.isEmpty()) {

            System.out.println(
                    "Belum ada surat masuk."
            );

            return;
        }

        tampilkanSuratMasuk();

        int nomorData = inputAngka(
                "Pilih nomor data yang ingin dihapus: "
        );

        if (nomorData < 1
                || nomorData > daftar.size()) {

            System.out.println(
                    "Nomor data tidak tersedia."
            );

            return;
        }

        controller.hapusSuratMasuk(
                nomorData - 1
        );
    }

    private void menuSuratKeluar() {

        boolean kembali = false;

        while (!kembali) {

            System.out.println();
            System.out.println("================================");
            System.out.println("KELOLA SURAT KELUAR");
            System.out.println("1. Lihat Surat Keluar");
            System.out.println("2. Tambah Surat Keluar");
            System.out.println("3. Edit Surat Keluar");
            System.out.println("4. Hapus Surat Keluar");
            System.out.println("5. Cari Surat");
            System.out.println("0. Kembali");
            System.out.println("================================");

            int pilihan = inputAngka(
                    "Pilih menu: "
            );

            if (pilihan == 1) {

                tampilkanSuratKeluar();

            } else if (pilihan == 2) {

                tambahSuratKeluar();

            } else if (pilihan == 3) {

                editSuratKeluar();

            } else if (pilihan == 4) {

                hapusSuratKeluar();

            } else if (pilihan == 5) {

                menuPencarianSurat();

            } else if (pilihan == 0) {

                kembali = true;

            } else {

                System.out.println(
                        "Menu tidak tersedia."
                );
            }
        }
    }

    private void tampilkanSuratKeluar() {

        ArrayList<SuratKeluar> daftar =
                controller.getDaftarSuratKeluar();

        if (daftar.isEmpty()) {

            System.out.println(
                    "Belum ada surat keluar."
            );

            return;
        }

        System.out.println();
        System.out.println("SURAT KELUAR");

        for (int i = 0; i < daftar.size(); i++) {

            System.out.println(
                    "Data ke-" + (i + 1)
            );

            daftar.get(i).tampilkanDaftarSurat();

            System.out.println();
        }
    }

    private void tambahSuratKeluar() {

        System.out.println();
        System.out.println("TAMBAH SURAT KELUAR");

        String perihal = inputTeks(
                "Perihal: "
        );

        tampilkanKategori();

        int kategori = inputAngka(
                "Pilih kategori: "
        );

        while (kategori < 1 || kategori > 13) {

            System.out.println(
                    "Kategori harus 1 sampai 13."
            );

            kategori = inputAngka(
                    "Pilih kategori: "
            );
        }

        String tanggal = inputTanggal(
                "Tanggal Keluar: "
        );

        String penerima = inputTeks(
                "Penerima: "
        );

        controller.tambahSuratKeluar(
                perihal,
                kategori,
                tanggal,
                penerima
        );
    }

    private void tampilkanKategori() {

        System.out.println();
        System.out.println("Kategori Surat:");
        System.out.println("1. SK");
        System.out.println("2. SU");
        System.out.println("3. SPm");
        System.out.println("4. SPb");
        System.out.println("5. SPp");
        System.out.println("6. SP");
        System.out.println("7. SM");
        System.out.println("8. ST");
        System.out.println("9. SKet");
        System.out.println("10. SR");
        System.out.println("11. SB");
        System.out.println("12. SRT");
        System.out.println("13. SPg");
    }

    private void editSuratKeluar() {

        ArrayList<SuratKeluar> daftar =
                controller.getDaftarSuratKeluar();

        if (daftar.isEmpty()) {

            System.out.println(
                    "Belum ada surat keluar."
            );

            return;
        }

        tampilkanSuratKeluar();

        int nomorData = inputAngka(
                "Pilih nomor data yang ingin diedit: "
        );

        if (nomorData < 1
                || nomorData > daftar.size()) {

            System.out.println(
                    "Nomor data tidak tersedia."
            );

            return;
        }

        SuratKeluar surat =
                daftar.get(nomorData - 1);

        System.out.println();
        System.out.println("Tekan ENTER jika data tidak ingin diubah.");

        System.out.println(
                "Perihal lama: "
                + surat.getPerihal()
        );

        String perihalBaru = input.nextLine();

        System.out.println(
                "Tanggal lama: "
                + surat.getTanggalKeluarSurat()
        );

        System.out.println(
                "Contoh tanggal: 08-02-2026"
        );

        String tanggalBaru = input.nextLine();

        System.out.println(
                "Penerima lama: "
                + surat.getPenerima()
        );

        String penerimaBaru = input.nextLine();

        controller.editSuratKeluar(
                nomorData - 1,
                perihalBaru,
                tanggalBaru,
                penerimaBaru
        );
    }

    private void hapusSuratKeluar() {

        ArrayList<SuratKeluar> daftar =
                controller.getDaftarSuratKeluar();

        if (daftar.isEmpty()) {

            System.out.println(
                    "Belum ada surat keluar."
            );

            return;
        }

        tampilkanSuratKeluar();

        int nomorData = inputAngka(
                "Pilih nomor data yang ingin dihapus: "
        );

        if (nomorData < 1
                || nomorData > daftar.size()) {

            System.out.println(
                    "Nomor data tidak tersedia."
            );

            return;
        }

        controller.hapusSuratKeluar(
                nomorData - 1
        );
    }

    private void tampilkanPenghuni() {

        ArrayList<Penghuni> daftar =
                controller.getDaftarPenghuni();

        if (daftar.isEmpty()) {
            System.out.println("Belum ada data penghuni.");
            return;
        }

        System.out.println();
        System.out.println("DATA PENGHUNI");

        for (int i = 0; i < daftar.size(); i++) {

            Penghuni penghuni = daftar.get(i);

            System.out.println(
                    "Data ke-" + (i + 1)
            );

            System.out.println(
                    "Nama        : "
                    + penghuni.getNama()
            );

            System.out.println(
                    "NIM         : "
                    + penghuni.getNim()
            );

            System.out.println(
                    "Asal Daerah : "
                    + penghuni.getAsalDaerah()
            );

            System.out.println(
                    "Fakultas    : "
                    + penghuni.getFakultas()
            );

            System.out.println(
                    "Jurusan     : "
                    + penghuni.getJurusan()
            );

            System.out.println(
                    "Status      : "
                    + penghuni.getStatus()
            );
            
            System.out.println(
                    "Keterangan  : "
                    + penghuni.getKeterangan()
            );

            System.out.println(
                    "--------------------------------"
            );
        }
    }
    
    private void menuPenghuni() {

        boolean kembali = false;

        while (!kembali) {

            System.out.println();
            System.out.println("================================");
            System.out.println("KELOLA DATA PENGHUNI");
            System.out.println("1. Lihat Data Penghuni");
            System.out.println("2. Tambah Penghuni");
            System.out.println("3. Edit Penghuni");
            System.out.println("4. Hapus Penghuni");
            System.out.println("0. Kembali");
            System.out.println("================================");

            int pilihan = inputAngka("Pilih menu: ");

            if (pilihan == 1) {
                tampilkanPenghuni();

            } else if (pilihan == 2) {
                tambahPenghuni();

            } else if (pilihan == 3) {
                editPenghuni();

            } else if (pilihan == 4) {
                hapusPenghuni();

            } else if (pilihan == 0) {
                kembali = true;

            } else {
                System.out.println("Menu tidak tersedia.");
            }
        }
    }

    private void tambahPenghuni() {

        System.out.println();
        System.out.println("TAMBAH PENGHUNI");

        String nama = inputTeks("Nama: ");
        String nim = inputTeks("NIM: ");
        String asalDaerah = inputTeks("Asal Daerah: ");
        String fakultas = inputTeks("Fakultas: ");
        String jurusan = inputTeks("Jurusan: ");
        String status = inputTeks("Status: ");

        System.out.print("Keterangan (boleh kosong): ");
        String keterangan = input.nextLine().trim();

        Penghuni penghuni = new Penghuni(
                nama,
                nim,
                asalDaerah,
                fakultas,
                jurusan,
                status,
                keterangan
        );

        controller.tambahPenghuni(penghuni);
    }

    private void editPenghuni() {

        String nim = inputTeks("Masukkan NIM penghuni yang ingin diedit: ");

        Penghuni penghuni = controller.cariPenghuni(nim);

        if (penghuni == null) {
            System.out.println("Penghuni dengan NIM tersebut tidak ditemukan.");
            return;
        }

        System.out.println();
        System.out.println("Tekan ENTER untuk mempertahankan data lama.");

        System.out.println("Nama lama: " + penghuni.getNama());
        String nama = input.nextLine().trim();
        if (!nama.isEmpty()) {
            penghuni.setNama(nama);
        }

        System.out.println("Asal daerah lama: " + penghuni.getAsalDaerah());
        String asalDaerah = input.nextLine().trim();
        if (!asalDaerah.isEmpty()) {
            penghuni.setAsalDaerah(asalDaerah);
        }

        System.out.println("Fakultas lama: " + penghuni.getFakultas());
        String fakultas = input.nextLine().trim();
        if (!fakultas.isEmpty()) {
            penghuni.setFakultas(fakultas);
        }

        System.out.println("Jurusan lama: " + penghuni.getJurusan());
        String jurusan = input.nextLine().trim();
        if (!jurusan.isEmpty()) {
            penghuni.setJurusan(jurusan);
        }

        System.out.println("Status lama: " + penghuni.getStatus());
        String status = input.nextLine().trim();
        if (!status.isEmpty()) {
            penghuni.setStatus(status);
        }

        System.out.println("Keterangan lama: " + penghuni.getKeterangan());
        String keterangan = input.nextLine().trim();
        if (!keterangan.isEmpty()) {
            penghuni.setKeterangan(keterangan);
        }

        controller.editPenghuni(nim, penghuni);
    }

    private void hapusPenghuni() {

        String nim = inputTeks("Masukkan NIM penghuni yang ingin dihapus: ");

        Penghuni penghuni = controller.cariPenghuni(nim);

        if (penghuni == null) {
            System.out.println("Penghuni dengan NIM tersebut tidak ditemukan.");
            return;
        }

        System.out.println("Nama: " + penghuni.getNama());
        System.out.println("NIM : " + penghuni.getNim());

        System.out.print("Yakin ingin menghapus data ini? (Y/N): ");
        String konfirmasi = input.nextLine().trim();

        if (konfirmasi.equalsIgnoreCase("Y")) {
            controller.hapusPenghuni(nim);
        } else {
            System.out.println("Penghapusan dibatalkan.");
        }
    }

    private void cariSuratBerdasarkanPihak() {

        System.out.println();
        System.out.println("CARI SURAT BERDASARKAN PIHAK");

        String pihak = inputTeks(
                "Masukkan pengirim/penerima: "
        );

        controller.cariSurat(pihak);
    }
    
    private void cariSuratBerdasarkanKategori() {

        System.out.println();
        System.out.println("CARI SURAT BERDASARKAN KATEGORI");

        tampilkanKategori();

        int kategori = inputAngka(
                "Pilih kategori: "
        );

        while (kategori < 1 || kategori > 13) {

            System.out.println(
                    "Kategori harus 1 sampai 13."
            );

            kategori = inputAngka(
                    "Pilih kategori: "
            );
        }

        controller.cariSurat(kategori);
    }
    
    private void menuPencarianSurat() {

        boolean kembali = false;

        while (!kembali) {

            System.out.println();
            System.out.println("================================");
            System.out.println("CARI SURAT");
            System.out.println("1. Berdasarkan Pengirim/Penerima");
            System.out.println("2. Berdasarkan Kategori");
            System.out.println("0. Kembali");
            System.out.println("================================");

            int pilihan = inputAngka(
                    "Pilih menu: "
            );

            if (pilihan == 1) {

                cariSuratBerdasarkanPihak();

            } else if (pilihan == 2) {

                cariSuratBerdasarkanKategori();

            } else if (pilihan == 0) {

                kembali = true;

            } else {

                System.out.println(
                        "Menu tidak tersedia."
                );
            }
        }
    }

    private String inputTeks(String pesan) {

        String teks = "";

        while (teks.trim().isEmpty()) {

            System.out.print(pesan);

            teks = input.nextLine();

            if (teks.trim().isEmpty()) {

                System.out.println(
                        "Input tidak boleh kosong."
                );
            }
        }

        return teks.trim();
    }

    private int inputAngka(String pesan) {

        int angka = 0;
        boolean benar = false;

        while (!benar) {

            System.out.print(pesan);

            try {

                angka = Integer.parseInt(
                        input.nextLine()
                );

                benar = true;

            } catch (NumberFormatException e) {

                System.out.println(
                        "Input harus berupa angka."
                );
            }
        }

        return angka;
    }

    private String inputTanggal(String pesan) {

        String tanggal = "";

        boolean benar = false;

        while (!benar) {

            System.out.print(pesan);

            tanggal = input.nextLine();

            String[] bagian =
                    tanggal.split("-");

            if (bagian.length == 3) {

                try {

                    int hari =
                            Integer.parseInt(bagian[0]);

                    int bulan =
                            Integer.parseInt(bagian[1]);

                    int tahun =
                            Integer.parseInt(bagian[2]);

                    if (hari >= 1
                            && hari <= 31
                            && bulan >= 1
                            && bulan <= 12
                            && tahun >= 2000) {

                        benar = true;

                    } else {

                        System.out.println(
                                "Tanggal tidak valid."
                        );
                    }

                } catch (NumberFormatException e) {

                    System.out.println(
                            "Format tanggal harus DD-MM-YYYY."
                    );
                }

            } else {

                System.out.println(
                        "Format tanggal harus DD-MM-YYYY."
                );
            }
        }

        return tanggal;
    }
}

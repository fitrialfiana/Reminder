/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.latihan.java;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author fiezi
 */
@Controller
public class ProjectController {
     
    
    @RequestMapping("/input")
    public String getHasil(HttpServletRequest data, Model mode){
        //Menampilkan ke index
        //Menambahkan variabel data
        String Nama = data.getParameter("var_NamaSayur");
        String Harga = data.getParameter("var_harga");
        String Jumlah = data.getParameter("var_quantity");
        String Bayar = data.getParameter("var_bayar");
        
        String diskon = "";

        //konversi
        Double inputharga = Double.valueOf(Harga);
        Double inputq = Double.valueOf(Jumlah);
        Double inputTotal = inputharga * inputq;
        Double getTotal = null;
        Double inputbayar = Double.valueOf(Bayar);
        String kekurangan = "";

        String awal = " " + inputTotal;
        mode.addAttribute("HargaAwal", awal);
        
        //Hitung diskon
        if (inputTotal < 16000)
        {
            getTotal = inputTotal -(inputTotal/100 * 0);
            diskon = "0%";
        }
        else if (inputTotal >= 16000 && inputTotal < 25000)
        {
            getTotal = inputTotal -(inputTotal/100 * 10);
            diskon = "10%";           
        }
        else if (inputTotal >= 25000)
        {
            getTotal = inputTotal -(inputTotal/100 * 15);
            diskon = "15%";
        }   
        
        //fungsi seteah hitung diskon
        Double kembalian = inputbayar - getTotal;
        
        //kekurangan uang
        if (inputbayar < getTotal)
        {
            kekurangan = "Uang anda kurang : " + kembalian;
        }
        else if (inputbayar > getTotal)
        {
            kekurangan = "Uang anda kembali : " + kembalian;
        }
        else
        {
            kekurangan = "your money is right";
        }        
        
        //Masukkan ke view / tampilkan
        mode.addAttribute("NamaSayur", Nama);
        mode.addAttribute("HargaSayur", Harga);
        mode.addAttribute("Quantity", Jumlah);
        mode.addAttribute("TotalHrg", getTotal);
        mode.addAttribute("Discount", diskon);
        mode.addAttribute("pembayaran", Bayar);
        mode.addAttribute("Kembali", kembalian);
        mode.addAttribute("Pesan", kekurangan);
        
        
        //Menampilkan viewDta
        return "viewData";

        
        //int hitung = Integer.sum(Harga, Jumlah);
        //return Nama + String.valueOf(Harga) + String.valueOf(Jumlah) + String.valueOf(Bayar) + String.valueOf(Jumlah);
    }
    
    
    
}

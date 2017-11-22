/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.phb.perpuswebapp.controller;

import lab.phb.perpuswebapp.repo.PerpusRepo;
import lab.phb.perpuswebapp.entity.Perpus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;

@Controller
public class WebController {

	@Autowired
	private PerpusRepo prpsRepo;

	@RequestMapping("/daftar-perpus")
	public void daftarPerpus(Model model) {
		model.addAttribute("daftarPerpus", 
			prpsRepo.findAll());
	}

	@RequestMapping("/tambah-data")
	public void tambahData(
		@ModelAttribute("prps") Perpus prps,
		BindingResult result) {}

	@RequestMapping(value = "/tambah-data",
		method = RequestMethod.POST) 
	public String simpanDataBaru(
		@ModelAttribute("prps") Perpus prps,
		BindingResult result) {
		System.out.println(prps.getId());
		prpsRepo.save(prps);

		return "redirect:/daftar-perpus";
	}

	@RequestMapping("/edit")
    public void getEditForm(
    		@RequestParam("id") String id, 
    		Model model) {
    	Perpus result = prpsRepo.findOne(id);
    	model.addAttribute("prps", result);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String simpanEditData(
    		@ModelAttribute("prps") Perpus prps,
    		BindingResult result) {
    	System.out.println("id : " + prps.getId());
    	prpsRepo.save(prps);
    	return "redirect:/daftar-perpus";
    }

	// @RequestMapping(value = "/edit", method = RequestMethod.GET)
	// public void getEditData(@RequestParam(name = "id", reuered =
	// 	false) String Id,
	// @ModelAttriubte("perpus") Perpus perpus, BindingResult binding){
	// 	Perpus perpus = PerpusRepo.findById(id);
	// 	perpus.setId(perpus.getId());
	// 	perpus.setJudul_buku(perpus.getJudul_buku());
	// 	perpus.setPenulis(perpus.getPenulis());

	// }
	// @RequestMapping(value ="/edit", method = RequestMethod.POST)
	// public String saveEditData(
	// 	@ModelAttribute("perpus")Perpus perpus, BindingResult binding){
	// 	PerpusRepo.save(perpus);
	// 	return "redirect:/daftar-perpus";
	// }
	@RequestMapping("/hapus")
    public String hapusData(@RequestParam("id") String id) {
        prpsRepo.delete(id);
        return "redirect:/daftar-perpus";
    }
}

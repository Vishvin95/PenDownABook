package com.pendownabook.web.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pendownabook.entities.PreviewBook;
import com.pendownabook.entities.Publisher;
import com.pendownabook.entities.ReviewStatus;
import com.pendownabook.service.PreviewBookService;
import com.pendownabook.service.PublisherService;
import com.pendownabook.service.ReviewStatusService;
import com.pendownabook.service.UploadService;

@Controller
@RequestMapping("/previewbook")
public class PreviewBookController {

	@Autowired
	private PreviewBookService previewBookService;

	@Autowired
	private UploadService uploadService;
	
	@Autowired
	private ReviewStatusService reviewStatusService;
	
	@Autowired
	private PublisherService publisherService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<PreviewBook> getPreviewBookForUser() {
		return previewBookService.getAll();
	}

	@RequestMapping(value = "/showpreviewbooks", method = RequestMethod.GET)
	@ResponseBody
	public List<PreviewBook> getPreviewBookForWriter(@RequestParam(name = "email") String writerEmail) {
		return previewBookService.getPreviewBook(writerEmail);
	}

	@RequestMapping(value = "/{email}", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getPreviewBookForPublisher(@PathVariable("email") String publisherEmail) {
		return previewBookService.getReviewsForPublisher(publisherEmail);
	}

	@RequestMapping(value = "/createpreviewbook/{email}", method = RequestMethod.POST)
	public ModelAndView createPreviewBook(@PathVariable("email") String writerEmail,
			@RequestParam("previewBookTitle") String previewBookTitle, @RequestParam("genre") Long genreId,
			@RequestParam("previewBookFile") MultipartFile previewBook) throws ParseException, IOException {

		String previewBookPath = uploadService.savePDF(previewBook, "PreviewBook", writerEmail);
		previewBookService.savePreviewBook(writerEmail, genreId, previewBookTitle, previewBookPath);
		return new ModelAndView("redirect:/home");
	}

	@RequestMapping(value = "/createreview/{email}/{previewBookId}", method = RequestMethod.POST)
	public ModelAndView createReviewForPreviewBook(@PathVariable("email") String publisherEmail,
			@PathVariable("previewBookId") Long previewBookId, @RequestParam("reviewstatus") Long reviewStatusId) throws ParseException{
		
			ReviewStatus reviewStatus = reviewStatusService.getByReviewStatusById(reviewStatusId);
			Publisher publisher = publisherService.getByEmail(publisherEmail);
			previewBookService.addPublisherReviewForPreviewBook(previewBookId,reviewStatus,publisher);
		
		return new ModelAndView("redirect:/publisher");
	}
}

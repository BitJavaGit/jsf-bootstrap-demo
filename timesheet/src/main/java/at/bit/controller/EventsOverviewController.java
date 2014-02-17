package at.bit.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import at.bit.model.Event;
import at.bit.repository.EventRepository;

@Component
@Scope(value = "view")
public class EventsOverviewController implements PaginationController<Event> {

	private static final int PAGE_SIZE = 5;

	private static final Logger LOGGER = LoggerFactory.getLogger(EventsOverviewController.class);

	@Autowired
	private EventRepository eventRepo;

	private Pageable pageable;
	private Page<Event> currentPage;
	private long currentPageNumber = 1;

	@PostConstruct
	@Transactional
	private void init() {
		pageable = new PageRequest(0, PAGE_SIZE);
		currentPage = eventRepo.findAll(pageable);
	}

	@Transactional
	public long totalEvents() {
		return eventRepo.count();
	}

	@Override
	public void prevPage() {
		currentPageNumber--;
	}

	@Override
	public void nextPage() {
		currentPageNumber++;
	}

	@Override
	public long pages() {
		LOGGER.debug("Event pages: " + currentPage.getTotalPages());
		return currentPage.getTotalPages();
	}

	@Override
	@Transactional
	public List<Event> fetchCurrentPage() {
		pageable = new PageRequest((int) currentPageNumber - 1, PAGE_SIZE);
		currentPage = eventRepo.findAll(pageable);
		return currentPage.getContent();
	}

	@Override
	public long getCurrentPage() {
		LOGGER.debug("Event current page: {}", currentPageNumber);
		return currentPageNumber;
	}

	@Override
	public void setCurrentPage(final long currentPage) {
		currentPageNumber = currentPage;
	}

}
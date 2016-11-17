package com.charitable.charity.crud.controller;

import com.charitable.charity.crud.model.Charity;
import com.charitable.charity.crud.model.SearchForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.regex.Pattern;


@Controller
public class CharityTemplateServer {

    @Autowired
    private final MongoTemplate mongoTemplate;

    @Autowired
    public CharityTemplateServer(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @RequestMapping("/view-charity")
    public String servePage(@RequestParam("id") String id, ModelMap model) {

        Query findingCharity = new Query(Criteria.where("id").is(id));
        Charity charity = mongoTemplate.findOne(findingCharity, Charity.class);
        model.put("charity", charity);

        return "charity-micropage";
    }


    //Search megaQuery
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String findCharityByQuery(@RequestParam("searchQuery") String searchQuery,
                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                     @RequestParam(value = "charSort", defaultValue = "0") String charSort,
                                     ModelMap modelMap) {

        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.
                        where("charityName").regex(Pattern.compile(Pattern.quote(searchQuery), Pattern.CASE_INSENSITIVE)), Criteria.where("charityAddress").regex(Pattern.compile(Pattern.quote(searchQuery), Pattern.CASE_INSENSITIVE)),
                Criteria.where("categoryList").regex(Pattern.compile(Pattern.quote(searchQuery), Pattern.CASE_INSENSITIVE)));

        Query query = new Query(criteria);

        List<Charity> charities = mongoTemplate.find(query, Charity.class);
        List<Charity> sortedCharities = sortCharities(charities, charSort);
        List<Charity> featuredCharities = findFeatured();

        modelMap.addAttribute("searchQuery", searchQuery);
        modelMap.addAttribute("charSort", charSort);
        modelMap.addAttribute("numberOfResults", charities.size());
        modelMap.addAttribute("previousPage", Math.max(page - 1, 0));
        modelMap.addAttribute("nextPage", page + 1);
        modelMap.addAttribute("charities", paginate(charities, page, pageSize));
        modelMap.addAttribute("sorted", sortedCharities);
        modelMap.addAttribute("customQuery", new SearchForm());
        modelMap.addAttribute("featured", featuredCharities);
        //modelMap.addAttribute("employees-text", setEmployeeText(getEmployeesNo()));

        return "search-results";
    }

    private List<Charity> sortCharities(List<Charity> charities, String sortBy) {
        List<Charity> toSort = new ArrayList<>(charities);
        if(sortBy != null){
            if(sortBy.equals("size")) {
                Collections.sort(toSort, new Comparator<Charity>() {
                    @Override
                    public int compare(Charity c1, Charity c2) {
                        return c1.getEmployeesNo() - c2.getEmployeesNo();
                    }
                });
                return toSort;

            } else if(sortBy.equals("donation")){
                Collections.sort(toSort, new Comparator<Charity>() {
                    @Override
                    public int compare(Charity o1, Charity o2) {
                        return o1.getAverageDonations() - o2.getAverageDonations();
                    }
                });
                return toSort;
            }
        }

        Collections.sort(toSort, new Comparator<Charity>() {
            @Override
            public int compare(Charity c1, Charity c2) {
                char first = c1.getCharityName().toUpperCase().charAt(0);
                char second = c2.getCharityName().toUpperCase().charAt(0);
                return first - second;
            }
        });
        System.out.println("sorted by " + sortBy);
        return toSort;
    }


    //Pagination
    private static List<Charity> paginate(List<Charity> allCharities, int page, int pageSize) {
        List<Charity> paginatedCharities = new ArrayList<>();

        int start = (page * pageSize);
        int end = Math.min(start + pageSize, allCharities.size());

        for (int i = start; i < end; i++) {
            paginatedCharities.add(allCharities.get(i));
        }

        return paginatedCharities;
    }

    public static Charity charity(int id) {
        Charity charity = new Charity();
        charity.setId(String.valueOf(id));
        return charity;
    }


    //Setting display info on charities
    private static String setEmployeeText(int employeeNo) {
        if (employeeNo < 50) {
            return "This is a small charity";
        } else if (employeeNo > 50 && employeeNo < 250) {
            return "This is a medium-size charity";
        } else {
            return "This is a large charity";
        }
    }


    //Featured Charities
    public List<Charity> findFeatured(){

        Query query = new Query();
        query.limit(5);
        query.with(new Sort(Sort.Direction.DESC, "_id"));

        List<Charity> charities = mongoTemplate.find(query, Charity.class);

        return charities;
    }


}

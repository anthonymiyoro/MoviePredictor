package am.moviepredictorai;

import am.moviepredictorai.movies.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import am.moviepredictorai.movies.*;

@Controller
@ComponentScan
public class MainController implements WebMvcConfigurer{
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home1");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
    }

    @Autowired  // This means to get the bean called userRepository
                // Which is auto-generated by Spring, we will use it to handle the data
    private movieRepository movieRepository;
    private userRepository userRepository;

    @GetMapping(path="/add") // Map ONLY GET Requests
    public @ResponseBody String addNewUser (@RequestParam String username
            , @RequestParam String likedmovies) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User();
        n.setUsername(username);
        n.setLikedMovies(likedmovies);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }


}

//@REquestMapping(value="/registration", method = RequestMethod.GET)
//public String showRegistrationForm(WebRequest request, Model model){
//    User
//}


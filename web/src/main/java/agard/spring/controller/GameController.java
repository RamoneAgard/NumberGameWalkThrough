package agard.spring.controller;

import agard.spring.service.GameService;
import agard.spring.util.AttributeNames;
import agard.spring.util.GameMappings;
import agard.spring.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class GameController {

    //Fields //
    private final GameService gameService;

    //Constructors //
    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    // Request Methods //
    @GetMapping(GameMappings.PLAY)
    public String play(Model model){
        model.addAttribute(AttributeNames.MAIN_MESSAGE, gameService.getMainMessage());
        model.addAttribute(AttributeNames.RESULT_MESSAGE, gameService.getResultMessage());
        log.debug("model = {}", model);
        return gameService.isGameOver() ? ViewNames.GAME_OVER : ViewNames.PLAY;
    }

    @PostMapping(GameMappings.PLAY)
    public String processMessage(@RequestParam int guess){
        log.debug("guess = {}", guess);
        gameService.checkGuess(guess);
        return GameMappings.REDIRECT_PLAY;
    }

    @GetMapping(GameMappings.RESTART)
    public String restart(){
        log.debug("resetting the game");
        gameService.reset();
        return GameMappings.REDIRECT_PLAY;
    }
}

package nl.trifork.tictactoe;

import nl.trifork.tictactoe.model.Game;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("game")
public class TicTacToeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index( @ModelAttribute("game") Game game) {
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String markTile(
            @ModelAttribute("game") Game game,
            @RequestParam("title_id") String tileId,
            @RequestParam(value = "new_game", required = false, defaultValue = "false") boolean newGame,
            @RequestParam(value = "player_go_first", required = false, defaultValue = "false") boolean playerGoFirst
    ) {
        if (newGame) {
            game.reset();
            game.setPlayerGoFirst(playerGoFirst);
            if (!playerGoFirst) {
                game.markTile("1-1");
            }
        } else {
            game.markTile(tileId); // Player turn
            game.markTileRandom(); // Computer turn

        }
        return "index";
    }

    @ModelAttribute("game")
    public Game populateGame() {
        return new Game();
    }
}
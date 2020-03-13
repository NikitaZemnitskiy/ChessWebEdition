package xyz.niflheim.stockfish;

import xyz.niflheim.stockfish.engine.enums.Query;
import xyz.niflheim.stockfish.engine.enums.QueryType;
import xyz.niflheim.stockfish.engine.enums.Variant;
import xyz.niflheim.stockfish.exceptions.StockfishInitException;

public class Main {

    public static void main(String[] args) throws StockfishInitException {
        StockfishClient client = new StockfishClient.Builder()
                .setInstances(4)
                .setVariant(Variant.BMI2)
                .build();
        Query query = new Query.Builder(QueryType.Legal_Moves)
                .setFen("rnbqkbnr/pppp1ppp/8/4p3/4P3/8/PPPP1PPP/RNBQKBNR w KQkq - 0 1")
                .build();
        client.submit(query, result -> {
            System.out.println(result);
        });
    }

}

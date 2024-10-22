package ir.ehsanmna.cookiclicker.utils.placeholder;

import ir.ehsanmna.cookiclicker.handler.CMSGHandler;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.UUID;

import static ir.ehsanmna.cookiclicker.handler.CMSGHandler.points;

public class CustomPlaceholder extends PlaceholderExpansion {


    @Override
    public @NotNull String getIdentifier() {
        return "cookieclicker";
    }

    @Override
    public @NotNull String getAuthor() {
        return "EhsanMNA";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        if (player == null) return null;
        String arg1 = params.split("_")[0];
        String arg2 = params.split("_")[1];
        if(arg1.equalsIgnoreCase("game")){
            if (arg2.equalsIgnoreCase("price")){
                int i = 0;
                for (Map.Entry<UUID, Integer> list : CMSGHandler.getTop5(points)){
                    if (list.getKey().equals(player.getUniqueId())){
                        float x = switch (i) {
                            case 1 -> 0.2f;
                            case 2 -> 0.1f;
                            case 3 -> 0.07f;
                            case 4 -> 0.03f;
                            default -> 1;
                        };
                        return ""+(int)(CMSGHandler.getPoints(player.getUniqueId())*x);
                    }
                    i++;
                }
                return "nothing";
            }
        }
        else if(arg1.equalsIgnoreCase("gamer")){
            if (arg2.equalsIgnoreCase("name"))
                return CMSGHandler.getRandomWinnerName();

            else if (arg2.equalsIgnoreCase("price"))
                return CMSGHandler.getRandomWinnerPrice();

            return "";
        }
        else if(arg1.equalsIgnoreCase("game1")){
            if (arg2.equalsIgnoreCase("name"))
                return CMSGHandler.getNameOfPlayer(CMSGHandler.getNthHighest(CMSGHandler.points,1).getKey());

            else if (arg2.equalsIgnoreCase("points"))
                return ""+CMSGHandler.getNthHighest(CMSGHandler.points,1).getValue();

            return "";
        }
        else if(arg1.equalsIgnoreCase("game2")){
            if (arg2.equalsIgnoreCase("name"))
                try {
                    String name = CMSGHandler.getNameOfPlayer(CMSGHandler.getNthHighest(CMSGHandler.points,2).getKey());
                    return name;
                }catch (Exception e){return "No one";}

            else if (arg2.equalsIgnoreCase("points"))
                try {return ""+CMSGHandler.getNthHighest(CMSGHandler.points,2).getValue();
                } catch (Exception e) {return "0";}

            return "";
        }
        else if(arg1.equalsIgnoreCase("game3")){
            if (arg2.equalsIgnoreCase("name"))
                try {
                    return CMSGHandler.getNameOfPlayer(CMSGHandler.getNthHighest(CMSGHandler.points,3).getKey());
                } catch (Exception e) {return "No one";}

            else if (arg2.equalsIgnoreCase("points"))
                try {
                    return ""+CMSGHandler.getNthHighest(CMSGHandler.points,3).getValue();
                } catch (Exception e) {return "0";}

            return "";
        }
        else if(arg1.equalsIgnoreCase("game4")){
            if (arg2.equalsIgnoreCase("name"))
                try {
                    return CMSGHandler.getNameOfPlayer(CMSGHandler.getNthHighest(CMSGHandler.points,4).getKey());
                } catch (Exception e) {
                    return "No one";
                }

            else if (arg2.equalsIgnoreCase("points"))
                try {
                    return ""+CMSGHandler.getNthHighest(CMSGHandler.points,4).getValue();
                } catch (Exception e) {
                    return "0";
                }

            return "";
        }
        else if(arg1.equalsIgnoreCase("game5")){
            if (arg2.equalsIgnoreCase("name"))
                try {
                    return CMSGHandler.getNameOfPlayer(CMSGHandler.getNthHighest(CMSGHandler.points,5).getKey());
                } catch (Exception e) {
                    return "No one";
                }

            else if (arg2.equalsIgnoreCase("points"))
                try {
                    return ""+CMSGHandler.getNthHighest(CMSGHandler.points,5).getValue();
                } catch (Exception e) {
                    return "0";
                }

            return "";
        }
        else if(arg1.equalsIgnoreCase("top1")){
            if (arg2.equalsIgnoreCase("name"))
                try {
                    return CMSGHandler.getNameOfPlayer(CMSGHandler.getNthHighest(CMSGHandler.points,1).getKey());
                } catch (Exception e) {
                    return "No one";
                }

            else if (arg2.equalsIgnoreCase("points"))
                try {
                    return ""+CMSGHandler.getNthHighest(CMSGHandler.points,1).getValue();
                } catch (Exception e) {
                    return "0";
                }

            return "";
        }
        else if(arg1.equalsIgnoreCase("top2")){
            if (arg2.equalsIgnoreCase("name"))
                try {
                    return CMSGHandler.getNameOfPlayer(CMSGHandler.getNthHighest(CMSGHandler.points,2).getKey());
                } catch (Exception e) {
                    return "No one";
                }

            else if (arg2.equalsIgnoreCase("points"))
                try {
                    return ""+CMSGHandler.getNthHighest(CMSGHandler.points,2).getValue();
                } catch (Exception e) {
                    return "0";
                }

            return "";
        }
        else if(arg1.equalsIgnoreCase("top3")){
            if (arg2.equalsIgnoreCase("name"))
                try {
                    return CMSGHandler.getNameOfPlayer(CMSGHandler.getNthHighest(CMSGHandler.points,3).getKey());
                } catch (Exception e) {
                    return "No one";
                }

            else if (arg2.equalsIgnoreCase("points"))
                try {
                    return ""+CMSGHandler.getNthHighest(CMSGHandler.points,3).getValue();
                } catch (Exception e) {
                    return "0";
                }

            return "";
        }

        return null;
    }
}

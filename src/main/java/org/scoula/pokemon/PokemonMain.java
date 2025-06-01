package org.scoula.pokemon;

import org.scoula.pokemon.dao.CapturedPokemonDao;
import org.scoula.pokemon.dao.CapturedPokemonDaoImp;
import org.scoula.pokemon.dao.PokemonDao;
import org.scoula.pokemon.dao.PokemonDaoImp;
import org.scoula.pokemon.vo.CapturedPokemonVO;
import org.scoula.pokemon.vo.PokemonVO;

import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PokemonMain {
    private static PokemonDao pd = new PokemonDaoImp();
    private static CapturedPokemonDao cpd = new CapturedPokemonDaoImp();
    private static Scanner sc;

    public static void main(String[] args) {
        int input = 0;
        sc = new Scanner(new InputStreamReader(System.in));
        boolean run = true;

        while (run) {
            printMainMenu();
            input = sc.nextInt();
            sc.nextLine();


            switch (input) {
                case 1: // 포켓몬 잡으러 가기
                    huntPokemon();
                    waitUntilEnter();
                    break;
                case 2: // 내 포켓몬 보기
                    printMyPokemons();
                    waitUntilEnter();
                    break;
                case 3: // 내 포켓몬 이름 바꾸기
                    changePokemonName();
                    waitUntilEnter();
                    break;
                case 4: // 포켓몬 풀어주기
                    freePokemon();
                    waitUntilEnter();
                    break;
                default:
                    run = false;
                    break;
            }
        }

        System.out.println("게임 종료");
    }

    private static void printMainMenu() {
        System.out.println("갓겜 포켓몬스터");
        System.out.println("1. 포켓몬 잡으러 가기");
        System.out.println("2. 내 포켓몬 보기");
        System.out.println("3. 내 포켓몬 이름 바꾸기");
        System.out.println("4. 포켓몬 풀어주기");
        System.out.println("5. 게임 종료");
    }

    private static void printMyPokemons() {
        try {
            List<CapturedPokemonVO> cps = null;
            cps = cpd.list();

            System.out.println("=============================");

            if(cps.size() == 0) System.out.println("없음");

            for (CapturedPokemonVO cp : cps) {
                System.out.println();
                PokemonVO pokemonInfo = pd.get(cp.getPokemonId());
                System.out.println("포켓몬: " + pokemonInfo.getName() + ", 이름: " + cp.getName());
                System.out.print("타입: " + pokemonInfo.getType());

                if(!pokemonInfo.getOtherType().isEmpty()) {
                    System.out.println(", " + pokemonInfo.getOtherType());
                } else {
                    System.out.println();
                }

                System.out.println("체력: " + pokemonInfo.getHp());
                System.out.println("공격력: " + pokemonInfo.getAttack() + ", 방어력: " + pokemonInfo.getDefense());
                System.out.println();
            }

            System.out.println("=============================");

        } catch (SQLException e) {
            System.out.println("포켓몬 정보를 불러오지 못했습니다.");
        }
    }

    private static void waitUntilEnter() {
        System.out.println("계속 하려면 엔터를 누르세요");
        sc.nextLine();
    }

    private static void huntPokemon() {
        try {
            List<PokemonVO> pokemons = pd.getList();

            Random random = new Random();
            PokemonVO randomPokemon = pokemons.get(random.nextInt(pokemons.size()));

            System.out.println("야생의 " + randomPokemon.getName() + " 이 나타났다!");
            System.out.println("1. 잡는다.");
            System.out.println("2. 놓아준다.");
            int input = 0;

            input = sc.nextInt();
            sc.nextLine();

            if(input == 1) {
                boolean capture = false;

                if(Math.random() >= 0.5) capture = true;

                if(capture) {
                    System.out.println(randomPokemon.getName() + "을 잡았다!!");
                    System.out.println("이름을 지어줄까요?");
                    System.out.println("1. 네");
                    System.out.println("2. 아니요");

                    input = sc.nextInt();
                    sc.nextLine();

                    CapturedPokemonVO myPokemon = new CapturedPokemonVO();

                    if(input == 1) {
                        System.out.print("이름을 입력하세요: ");
                        String name = sc.nextLine();

                        myPokemon.setName(name);
                        myPokemon.setPokemonId(randomPokemon.getId());
                    } else {
                        myPokemon.setName(randomPokemon.getName());
                        myPokemon.setPokemonId(randomPokemon.getId());
                    }

                    int result = cpd.insert(myPokemon);
                    System.out.println(myPokemon.getName() + "을 잡았다!!!");
                    System.out.println("포켓몬을 잡는데 성공했습니다!");
                } else {
                    System.out.println("앗 손이 미끌어져 버렸다...");
                    System.out.println("포켓몬을 잡지 못했습니다.");
                }

            } else {
                System.out.println("잘가 " + randomPokemon.getName());
                System.out.println("포켓몬을 놓아주었습니다.");
            }
        } catch (SQLException e) {
            System.out.println("포켓몬들이 다 어디갔나요?");
            System.out.println("시스템 에러");
        }
    }

    private static void changePokemonName() {
        try {
            System.out.println("=============================");
            List<CapturedPokemonVO> l = cpd.list();

            if(l.size() == 0) {
                System.out.println("이름 지어줄 포겟몬이 없습니다.");
            } else {
                for(int i = 0; i < l.size(); i++) {
                    System.out.println(i + 1 + ". " + l.get(i).getName());
                }

                System.out.println("이름 지을 포켓몬 번호를 입력하세요 ");
                int input = sc.nextInt() - 1;
                sc.nextLine();

                if(input >= 0 && input < l.size()) {
                    CapturedPokemonVO myPokemon = l.get(input);

                    System.out.print("이름을 입력하세요: ");
                    String name = sc.nextLine();

                    myPokemon.setName(name);
                    cpd.updateName(myPokemon);

                    System.out.println("너는 이제 " + name + "!!!");
                    System.out.println(myPokemon.getName() + " 행복해 보입니다.");
                } else {
                    System.out.println("포켓몬 이름을 바꾸지 않았습니다.");
                }
            }

            System.out.println("=============================");
        } catch (SQLException e) {
            System.out.println("포켓몬들이 다 어디갔나요?");
            System.out.println("시스템 에러");
            System.out.println("=============================");
        }
    }

    private static void freePokemon() {
        try {
            List<CapturedPokemonVO> l = cpd.list();

            System.out.println("=============================");


            if(l.size() == 0) {
                System.out.println("놓아줄 포겟몬이 없습니다.");
            } else {
                for(int i = 0; i < l.size(); i++) {
                    System.out.println(i + 1 + ". " + l.get(i).getName());
                }

                System.out.println("놓아줄 포켓몬 번호를 입력하세요 ");
                int input = sc.nextInt() - 1;
                sc.nextLine();

                if(input >= 0 && input < l.size()) {
                    System.out.println("잘가 " + l.get(input).getName());
                    System.out.println(l.get(input).getName() + "을 풀어주었다.");
                    cpd.delete(l.get(input).getId());
                } else {
                    System.out.println("포켓몬을 놓아줄 수 없어!");
                    System.out.println("포켓몬을 놓아주지 않았습니다.");
                }
            }

            System.out.println("=============================");
        } catch (SQLException e) {
            System.out.println("포켓몬들이 다 어디갔나요?");
            System.out.println("시스템 에러");
        }
    }
}

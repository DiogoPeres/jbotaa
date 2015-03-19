Sumário do paper:

PARTE 1:
Em co-evolution populações concorrentes podem, reciprocamente, levar ambas a uma grande complexidade, produzindo uma espécie de braço de ferro evolucionário.
No caso de caçador / presa, o sucesso dos predadores significa o insucesso das presas. Quando a presa evolui mais do que o predador, cria um novo desafio para este e vice-versa, o que pode levar a uma grande complexidade.
O problema desta evolução é que pode-nos levar a uma situação do típico jogo pedra / papel / tesoura, ou seja, a um ciclo sem evolução.
Então a preocupação é evitar os ciclos que levam a pouca evolução e evitar os "arm races" que levam a um nível de complexidade absurdo.

PARTE 2:

Red Queen Effect:
Em co-evo a probabilidade da reprodução de um organismo com certos traços podem ser mudados pelos seus concorrentes, isto é, mudanças numa espécie afeta o valor da reprodução de uma combinação de traços noutras espécies. Isto pode levar a que o progresso de uma geração seja reduzido ou eliminado pelas espécies concorrentes.
Isto traz problemas ao desempenho (fitness): periods of stasis in the fitness value of the two populations may correspond to a period of tightly-coupled co-evolution. Para resolver este problema adopta-se técnicas, como por exemplo: Master Tournament: Consiste em testar a performance do melhor individuo de cada geração contra o melhor individuo de todos as gerações. Isto pode ser usado para seleccionar as melhores soluções.

Evolution of predator and prey robots: a simple case:
Num caso simples os resultados mostraram que gerações mais tardias não têm melhores resultados que gerações mais recentes. Também nesse caso foram encontrados ciclos.

Testing individuals against all discovered solutions:
Para isto usam o método HALL OF FAME: To ensure progress, we may want to save individuals for an arbitrarily long time and continue testing against them. To this end, we introduce the ‘Hall of Fame’,which extends elitism in time for purposes of testing. The best individual from every generation, is retained for future testing.
Um individuo é testado contra outros 10 (escolhidos aleatoriamente de gerações passadas). Os resultados indicaram uma melhor performance. The average fitness of the best individuals tested against all best competitors progressively increases throughout generations.
O facto de estes individuos serem testados contra várias estratégias (10 individuos de outras gerações) permite ao algoritmo evolucionário descobrir novas estratégias mais gerais.
Resultados indicam que o método HALL OF FAME é um pouco melhor que o standart.

How the length of ‘arms races’ may vary in different conditions:
Na experiencia a presa tem os sensores limitados e só pode ver o predador a uma certa distancia. Com esta limitação a evolução da presa está limitada. Para competir contra os predadores têm de mudar de estratégia quando o predador já tem a estratégia "perfeita". Se deixarmos que a presa possa ver a longas distancias, pode levar a que elas ultrapassem os predadores refinando a sua estratégia.
Com cameras / sensores iguais, usando standart algoritm, chega-se à conclusão que os "arms races" continuam a ser muito produtivos e encontram boas soluções em ambas as gerações, sem cair em ciclo infinito. Depois, fazendo os mesmos testes com o Hall Of Fame, obteve-se ainda melhores resultados. No entanto se testarmos individuos standart contra hall of fame, verifica-se que os standart tendem a superar os Hall Of Fame. Com isto, embora as gerações hall of fame escapem melhor dos ciclos, não produzem necessariamente melhores soluções. No entanto tudo muda quando se muda os sensores, daí ser importante usar robots reais.

The role of environmental richness:
Os arms races podem variar consoante diferentes condições, isto é, pode-se ter ciclos finitos (infinitos) sem evolução, como pode-se ter evoluções de geração para geração durante muitas gerações.
Uma das maneiras de evitar os ciclos sem evolução é a com a riqueza do ambiente. Na co-evolução os concorrentes têm o mesmo ambiente. Podemos supor que a probabilidade de uma mudança repentina no comportamento que vai
produzir indivíduos viáveis ​​é inversamente proporcional à riqueza do ambiente
que não é submetido à co-evolução. Por exemplo, o evitar obstáculos que não faz parte da co-evolução não fará parte do processo de mudança de estratégia.
Os resultados indicam que quanto mais o ambiente estiver enriquecido, mais atrasará o processo de ciclo sem evolução.

How co-evolution can enhance the adaptive power of artificial evolution:
Basicamente testou-se se a evolução simples seria melhor do que a co-evolução. Verificou-se que nos casos simples era verdade, a evolução simples dava melhores resultados, mas ao criar-se mais dificuldades, enriquecendo o ambiente e/ou diminuindo a capacidades/sensores dos drones, verificou-se que as primeiras gerações tinham uma performance a tender para zero, ou seja, maus resultados. Também verificou-se que a evolução simples pode criar boas presas contra os melhores predadores, porque a estratégia passou por andar mais depressa mesmo estando longe dos predadores, ou seja, evita-los a qualquer custo.

PARTE 3 - Problemas a considerar:
Bootstrap problem: individuos das primeiras gerações teram grandes dificuldades com tarefas complexas. Isto, porque esta evolução começa sempre do zero.
Também há sempre o problemas dos critério e recompensas para certos comportamentos que podem influenciar a evolução.
Outro problema é a complexidade que pode chegar os "arms races".
Outro será os ciclos sem evolução.

3.1 A dynamical view of adaptation:
Como visto, a co-evolução produz estratégia demasiado complexas para enfrentar evoluções simples, mas há casos em que estratégias simples podem fazer frente e obter melhores resultados contra a co-evolução.
These results point to the conclusion that in certain tasks it is always possible to find a simple strategy that is able to defeat another single, albeit complex and general, strategy (although such simple strategy is a specialized strategy, i.e. it is able to defeat only that individual complex and general strategy and, of course, other similar strategies). If this is really true, in other words, if completely general solutions do not exist in some cases, we should re-consider the ‘cycling problem’. From this point of view, the fact of a co-evolutionary dynamics leading to a limit cycle in which the same type of solutions are adopted over and over again should not be considered as a failure but as an optimal solution. We cannot complain that co-evolution does not find a more general strategy able to cope with all the strategies adopted by the co-evolving population during a cycle if such general strategies do not exist.
The best that can be done is to select the appropriate strategy for the current counter-strategy, which is actually what happens when the co-evolutionary dynamics ends in a limit cycle.
package cz.kudladev.Day01


fun main(){
    val classLoader = Thread.currentThread().contextClassLoader
    val inputStream = classLoader.getResourceAsStream("day01-input.txt")
    val content = inputStream?.bufferedReader().use { it?.readLines() }

    if (content == null){
        return;
    }

    val parsedContent = parseText(content);

    val sortedFirstList = parsedContent.first.sortedDescending().reversed()
    val sortedSecondList = parsedContent.second.sortedDescending().reversed()

    val result = sortedFirstList.mapIndexed { index, it->
        it - sortedSecondList[index]
    }.map { if (it < 0) -it else it }

    println("Part one result: ${result.sum()}")

    val similarity = sortedFirstList.map { first ->
        first * sortedSecondList.filter { second ->
            first == second
        }.size
    }

    println("Part two result: ${similarity.sum()}")
}


fun parseText(content: List<String>): Pair<List<Int>,List<Int>>{
    val pairs = Pair(mutableListOf<Int>(),mutableListOf<Int>())
    content.forEach {
        val split = it.split("   ")
        pairs.first.add(split[0].toInt())
        pairs.second.add(split[1].toInt())
    }
    return pairs
}
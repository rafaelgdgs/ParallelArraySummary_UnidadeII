package main

import (
	"fmt"
	"math"
	"math/rand"
	"sync"
	"time"
)

type Object struct {
	ID    int
	Total float64
	Grupo int
}

func loadArray(N int) []Object {
	var array []Object
	for i := 1; i <= int(math.Pow(10, float64(N))); i++ {
		array = append(array, Object{i, rand.Float64() * 10, rand.Intn(5) + 1})
	}
	return array
}

func processArray(array []Object, T int) {
	var wg sync.WaitGroup
	startTime := time.Now()

	taskChan := make(chan Object, len(array))
	resultsChan := make(chan Result, len(array)*4)

	for _, obj := range array {
		taskChan <- obj
	}
	close(taskChan)

	for i := 0; i < T; i++ {
		wg.Add(1)
		go func() {
			defer wg.Done()
			processTasks(taskChan, resultsChan)
		}()
	}

	go func() {
		wg.Wait()
		close(resultsChan)
	}()

	var results []Result
	for result := range resultsChan {
		results = append(results, result)
	}

	printResults(results)

	elapsedTime := time.Since(startTime)
	fmt.Printf("Tempo gasto na etapa de Processamento: %.6fms\n", elapsedTime.Seconds()*1000)
}

func printResults(results []Result) {
	var total float64
	groupTotals := make(map[int]float64)
	countTotalLessThan5 := 0
	countTotalGreaterEqual5 := 0

	for _, result := range results {
		switch result.Type {
		case Total:
			total += result.Value
		case Subtotal:
			groupTotals[result.Group] += result.Value
		case CountLessThan5:
			countTotalLessThan5 += int(result.Value)
		case CountGreaterEqual5:
			countTotalGreaterEqual5 += int(result.Value)
		}
	}

	fmt.Printf("Nº de elementos com Total >= 5: %d\n", countTotalGreaterEqual5)
	fmt.Printf("Somatório dos Totais: %.2f\n", total)
	fmt.Printf("Nº de elementos com Total menor que 5: %d\n", countTotalLessThan5)
	fmt.Println("Somatório dos subtotais por grupo:")
	for group, subtotal := range groupTotals {
		fmt.Printf("Grupo %d: %.2f\n", group, subtotal)
	}
}

func processTasks(taskChan <-chan Object, resultsChan chan<- Result) {
	for task := range taskChan {
		switch {
		case task.Total >= 5:
			resultsChan <- Result{Type: CountGreaterEqual5, Value: 1}
			resultsChan <- Result{Type: Total, Value: task.Total}
		case task.Total < 5:
			resultsChan <- Result{Type: CountLessThan5, Value: 1}
		}
		resultsChan <- Result{Type: Subtotal, Group: task.Grupo, Value: task.Total}
	}
}

type ResultType int

const (
	Total ResultType = iota
	Subtotal
	CountLessThan5
	CountGreaterEqual5
)

type Result struct {
	Type  ResultType
	Group int
	Value float64
}

func main() {
	NValues := []int{5, 7, 9}
	TValues := []int{1, 4, 16, 64, 256}

	for _, N := range NValues {
		for _, T := range TValues {
			fmt.Printf("\nTeste com N=%d, T=%d:\n", N, T)

			array := loadArray(N)
			processArray(array, T)
		}
	}
}


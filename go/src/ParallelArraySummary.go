package main

import (
	"fmt"
	"math"
	"math/rand"
	"sync"
	"time"
)

type object struct {
	id, grupo int
	total     float64
}

func loadArray(N int) []object {
	var array []object
	for i := 1; i <= int(math.Pow10(N)); i++ {
		array = append(array, object{i, rand.Intn(5) + 1, rand.Float64() * 10})
	}
	return array
}

func processArray(array []object, T int) {
	var wg sync.WaitGroup
	wg.Add(T)
	startTime := time.Now()

	for i := 0; i < T; i++ {
		go func(id int) {
			defer wg.Done()
			if id == 0 {
				var sum float64
				for _, obj := range array {
					sum += obj.total
				}
				fmt.Printf("Somatório dos Totais: %.2f\n", sum)
			}
			if id == 1 {
				groupSum := make(map[int]float64)
				for _, obj := range array {
					groupSum[obj.grupo] += obj.total
				}
				fmt.Println("Somatório dos subtotais por grupo:")
				for group, sum := range groupSum {
					fmt.Printf("Grupo %d: %.2f\n", group, sum)
				}
			}
			if id == 2 {
				var count int
				for _, obj := range array {
					if obj.total < 5 {
						count++
					}
				}
				fmt.Printf("Nº de elementos com Total menor que 5: %d\n", count)
			}
			if id == 3 {
				var count int
				for _, obj := range array {
					if obj.total >= 5 {
						count++
					}
				}
				fmt.Printf("Nº de elementos com Total >= 5: %d\n", count)
			}
		}(i)
	}
	wg.Wait()

	elapsedTime := time.Since(startTime)
	fmt.Printf("Tempo gasto na etapa de Processamento: %s\n", elapsedTime)
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

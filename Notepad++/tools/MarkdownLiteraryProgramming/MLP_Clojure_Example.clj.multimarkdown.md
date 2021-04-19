<link rel="stylesheet" href="./js/prismjs/prism.css" />
<script src="./js/prismjs/prism.js"></script>

<link rel="stylesheet" href="./js/mermaidjs/mermaid.css" />
<script src="./js/mermaidjs/mermaid.min.js"></script>
<script>mermaid.initialize({startOnLoad:true});</script>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="./css/github-markdown.css">
<style>
	.markdown-body {
		box-sizing: border-box;
		min-width: 200px;
		max-width: 980px;
		margin: 0 auto;
		padding: 45px;
	}

	@media (max-width: 767px) {
		.markdown-body {
			padding: 15px;
		}
	}
</style>



<article class="markdown-body">

# Table of Contents 

{{TOC}}

# Markdown literary programming Example

## example01: Code with syntax highlighting.

```clojure
(defn f [[evens odds total amax amin] x]
  (let [[evens odds] (cond 
                       (even? x) [(inc evens ) odds]
                       (odd? x)  [evens (inc odds)]
                       :else     [evens odds])
        total (+ total x)
        amax  (max amax x)
        amin  (min amin x)]   
     [evens odds total amax amin]))
;The comment of the code requires at least two line comment characters
(reduce f [0 0 0 ##-Inf ##Inf] [5 6 8 -3 -9 11 156 6 7])
```

return `[4 5 187 156 -9]`

## example02: Mermaid flow chart

```mermaid
graph LR
      A-->B
      B-->C
      C-->A
      D-->C
```

## example03: Image

![cowplot](./image/cowplot.png)



</article>
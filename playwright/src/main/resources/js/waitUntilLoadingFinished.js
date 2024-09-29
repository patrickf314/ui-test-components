// Waits until an element does not have the data-loading attribute
// noinspection BadExpressionStatementJS
/**
 * Waits for the given node to not have to attribute data-loading=true.
 *
 * @param {HTMLElement} node the node
 * @param {{timeout: number}} arg the argument
 * @returns {Promise<void>} the promise resolving when the input is not loading
 */
(node, {timeout}) => new Promise((resolve, reject) => {
    let counter = timeout * 20;

    function checkLoading() {
        counter--;
        if (counter === 0) {
            reject(new Error(`Element is still disabled after ${timeout} seconds.`));
            return;
        }

        if (node.dataset.loading === 'true' && document.contains(node)) {
            setTimeout(checkLoading, 50);
        } else {
            resolve();
        }
    }

    checkLoading();
})